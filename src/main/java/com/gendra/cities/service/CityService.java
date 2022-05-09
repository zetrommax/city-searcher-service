package com.gendra.cities.service;

import com.gendra.cities.model.GeonameDto;
import com.gendra.cities.model.GeonameEntity;
import com.gendra.cities.repository.ICitiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CityService implements ICityService{
    @Autowired
    private ICitiesRepository citiesRepository;

    @Override
    public List<GeonameDto> search(Optional<String> name,
                                      Optional<Double> latitude,
                                      Optional<Double> longitud) throws Exception {
        List<GeonameEntity> listaGeonameEntity = citiesRepository.readData();
        List<GeonameDto> listGeonameDto = new ArrayList<>();
        Map<Integer, Double> scoreNames = new HashMap<>();

        //search by name
        if(name.isPresent() && !name.get().trim().isEmpty()){
            String searchName = name.get().trim().toLowerCase();
            //filter by name
            listaGeonameEntity = listaGeonameEntity.stream()
                    .filter(gn ->
                            gn.getName().toLowerCase().contains(searchName))
                    .collect(Collectors.toList());
            //evaluate name coincident
            for(GeonameEntity gn:listaGeonameEntity){
                double numLettersName = gn.getName().length();
                double numLettersSearchName = searchName.length();
                scoreNames.put(gn.getId(),numLettersSearchName/numLettersName);
            }
        }

        //search by coordinates
        if(latitude.isPresent() && longitud.isPresent()){
            BigDecimal searchLatitude = BigDecimal.valueOf(latitude.get());
            BigDecimal searchLongitud = BigDecimal.valueOf(longitud.get());
            Map<Integer,Double> bestLatitudes = new HashMap<>();
            listaGeonameEntity.stream()
                    .forEach(gn->{
                        BigDecimal lat = new BigDecimal(gn.getLatitude());
                        BigDecimal lon = new BigDecimal(gn.getLongitud());

                        BigDecimal evaluation = searchLatitude.subtract(lat).abs()
                                .add(searchLongitud.subtract(lon).abs());

                        bestLatitudes.put(gn.getId(), getCoordinateScore(evaluation.doubleValue()));
                    });

            List<Map.Entry<Integer, Double>> bestLatitudesSorted = bestLatitudes.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

            for(Map.Entry<Integer, Double> cs: bestLatitudesSorted){
                GeonameDto geonameDto = new GeonameDto();

                Optional<GeonameEntity> optGeoname = listaGeonameEntity.stream().filter(l -> l.getId().equals(cs.getKey()))
                        .findFirst();

                GeonameEntity geonameEntity = new GeonameEntity();

                if(optGeoname.isPresent()){
                     geonameEntity = optGeoname.get();
                }


                geonameDto.setLongitud(geonameEntity.getLongitud());
                geonameDto.setLatitude(geonameEntity.getLatitude());
                geonameDto.setName(geonameEntity.getName() + ", " + geonameEntity.getAdmin1() + ", " + geonameEntity.getCountry());

                //calculate score between nameScore and coordinateScore
                Double score = (scoreNames.get(cs.getKey())+cs.getValue())/2;

                geonameDto.setScore(BigDecimal.valueOf(score).setScale(1,RoundingMode.HALF_DOWN).toPlainString());
                listGeonameDto.add(geonameDto);
            }
        }else{
            if(name.isPresent()){
                for(GeonameEntity gn: listaGeonameEntity){
                    GeonameDto geonameDto = new GeonameDto();
                    geonameDto.setLongitud(gn.getLongitud());
                    geonameDto.setLatitude(gn.getLatitude());
                    geonameDto.setName(gn.getName() + ", " + gn.getAdmin1() + ", " + gn.getCountry());
                    geonameDto.setScore(BigDecimal.valueOf(scoreNames.get(gn.getId())).setScale(1,RoundingMode.HALF_UP).toPlainString());
                    listGeonameDto.add(geonameDto);
                }
            }else{
                throw new Exception("You must provide valid parameters for search");
            }
        }

        return listGeonameDto;
    }

    private double getCoordinateScore(double evaluation) {
        double scoreCoordinate = 0.0;

        if(evaluation==0){
            scoreCoordinate = 1;
        }

        int auxIndex= 10;
        for(int i = 1; i<900; i+=100 ){
            auxIndex--;

            if(evaluation>=i && evaluation<i+100){
                scoreCoordinate = auxIndex/10;
            }
        }

        return scoreCoordinate;
    }


}
