package com.gendra.cities.service;

import com.gendra.cities.model.GeonameDto;
import com.gendra.cities.model.GeonameEntity;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    public List<GeonameDto>  search(Optional<String> name, Optional<Double> latitude, Optional<Double> longitud) throws Exception;

}
