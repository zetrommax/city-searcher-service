package com.gendra.cities.repository;

import com.gendra.cities.model.GeonameEntity;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class CitiesRepository implements ICitiesRepository{
    private static final int BUFFER_SIZE = 64 * 1024;

    public List<GeonameEntity> readData() {
        List<GeonameEntity> listGeoname = new ArrayList<>();

        Storage storage = StorageOptions.getDefaultInstance().getService();

        Blob blob = storage.get("eighth-bivouac-349504.appspot.com",
                "data/cities.tsv");
        String fileContent = new String(blob.getContent());

        int index=0;
        String[] content = fileContent.split("\n");
        for (String  record:
                content) {
            index++;
            if (index==1){
                continue;
            }
            GeonameEntity geonameEntity = new GeonameEntity();
            String[] lineItems = record.split("\t"); //splitting the line and adding its items in String[]
            geonameEntity.setId(Integer.valueOf(lineItems[0]));
            geonameEntity.setName(lineItems[1]);
            geonameEntity.setAsciiname(lineItems[2]);
            geonameEntity.setAltName(lineItems[3]);
            geonameEntity.setLatitude(lineItems[4]);
            geonameEntity.setLongitud(lineItems[5]);
            geonameEntity.setFeatClass(lineItems[6]);
            geonameEntity.setFeatCode(lineItems[7]);
            geonameEntity.setCountry(lineItems[8]);
            geonameEntity.setCc2(lineItems[9]);
            geonameEntity.setAdmin1(lineItems[10]);
            geonameEntity.setAdmin2(lineItems[11]);
            geonameEntity.setAdmin3(lineItems[12]);
            geonameEntity.setAdmin4(lineItems[13]);
            geonameEntity.setPopulation(lineItems[14]);
            geonameEntity.setElevation(lineItems[15]);
            geonameEntity.setDem(lineItems[16]);
            geonameEntity.setTz(lineItems[17]);
            geonameEntity.setModifiedAt(lineItems[18]);
            listGeoname.add(geonameEntity);
        }

        return listGeoname;
    }
}
