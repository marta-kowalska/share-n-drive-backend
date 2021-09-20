package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.CarType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CarService {

    public List<Car> getFeaturedCars() {
        return null;
    }

    public List<Car> getAvailableCarsByDate(LocalDate from, LocalDate to) {
        return null;
    }

    public List<Car> getAvailableCarsByType(List<CarType> types) {
        return null;
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car){
        return 0;
    } //TODO: think about location for this method
}
