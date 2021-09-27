package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;

import java.util.List;

public interface CarRepositoryCustom {

        List<Car> findCarsByStringValue(List<String> values, String paramName);

        List<Car> findCarsByFuelType(List<String> fuelTypes);

        List<Car> findCarsByBodyType(List<String> bodyTypes);

        List<Car> findCarsByCarType(List<String> carTypes);

        List<Car> findCarsByTransmissionType(List<String> transmission);
}
