package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.CarType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class CarService {

    public List<Car> getFeaturedCars() {
        return null;
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car){
        return 0;
    } //TODO: think about location for this method

    public List<Car> getAvailableCarsByFilter(Map<String, String> params) {
        return null;
    }
}
