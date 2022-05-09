package com.gendra.cities.repository;

import com.gendra.cities.model.GeonameEntity;

import java.util.List;

public interface ICitiesRepository {
    public List<GeonameEntity> readData();
}
