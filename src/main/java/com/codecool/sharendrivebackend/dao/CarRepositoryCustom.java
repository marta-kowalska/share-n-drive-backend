package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;

import java.util.List;
import java.util.Map;

public interface CarRepositoryCustom {
    List<Car> findCarsByCriteria(Map<String, String> params);
}
