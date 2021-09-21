package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.model.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getFeaturedCars() {
        return null;
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car){
        return 0;
    } //TODO: think about location for this method

    public List<Car> getAvailableCarsByFilter(Map<String, String> params) {
        return carRepository.findCarsByCriteria(params);
    }
}
