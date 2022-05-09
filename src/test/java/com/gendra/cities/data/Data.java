package com.gendra.cities.data;

import com.gendra.cities.model.GeonameDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private static String testDataResult = "[{\"name\":\"Cameron, TX, US\",\"latitude\":\"30.85325\",\"longitud\":\"-96.97693\",\"score\":\"0.7\"},{\"name\":\"Cameron Park, CA, US\",\"latitude\":\"38.66879\",\"longitud\":\"-120.98716\",\"score\":\"0.6\"},{\"name\":\"American Fork, UT, US\",\"latitude\":\"40.3769\",\"longitud\":\"-111.79576\",\"score\":\"0.6\"},{\"name\":\"Cameron Park Colonia, TX, US\",\"latitude\":\"25.97147\",\"longitud\":\"-97.47832\",\"score\":\"0.5\"},{\"name\":\"Americus, GA, US\",\"latitude\":\"32.07239\",\"longitud\":\"-84.23269\",\"score\":\"0.7\"},{\"name\":\"Cameron Park, TX, US\",\"latitude\":\"25.96452\",\"longitud\":\"-97.47665\",\"score\":\"0.6\"},{\"name\":\"Cameron, MO, US\",\"latitude\":\"39.74028\",\"longitud\":\"-94.24106\",\"score\":\"0.7\"},{\"name\":\"American Canyon, CA, US\",\"latitude\":\"38.17492\",\"longitud\":\"-122.2608\",\"score\":\"0.6\"}]";

    public static List<GeonameDto> resultsGeoname(){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<GeonameDto>>(){}.getType();
        return gson.fromJson(testDataResult, listType);
    }
}
