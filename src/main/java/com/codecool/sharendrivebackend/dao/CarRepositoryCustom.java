package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface CarRepositoryCustom {

        List<Car> findCarsByBrand(List<String> brands);

        List<Car> findCarsByCriteria1(Map<String, List<String>> params);
}
