package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.model.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car){
        return 0;
    } //TODO: think about location for this method


    private Map<String, List<String>> getUniqueParams(Map<String, String> params) { //TODO check if necessary
        Map<String, List<String>> checkedParams = new HashMap<>();
        for(String param : params.keySet()){
                List<String> values = Arrays.stream(params.get(param).split(","))
                    .distinct()
                    .collect(Collectors.toList());
                checkedParams.put(param.toLowerCase(), values);
        }
        return checkedParams;
    }

    public List<Car> getFilteredCars1(Map<String, String> params) {
        Map<String, List<String>> checkedParams = getUniqueParams(params);
        return carRepository.findCarsByCriteria1(checkedParams);
    }

    public List<Car> getFilteredCars2(Map<String, String> params){
        List<Car> foundCars = new ArrayList<>();
        Map<String, List<String>> checkedParams = getUniqueParams(params);
        for (String key : params.keySet()) {
            switch (key) {
                case "brand":
                    foundCars.addAll(carRepository.findCarsByBrand(checkedParams.get(key)));
                    break;
                case "price":
                    int maxPrice = Integer.parseInt((checkedParams.get(key)).get(0));
                    foundCars.addAll(carRepository.findByPriceLessThanEqual(maxPrice));
                    break;
                case "seat_number":
                    int minSeatNumber = Integer.parseInt((checkedParams.get(key)).get(0));
                    foundCars.addAll(carRepository.findBySeatNumberGreaterThanEqual(minSeatNumber));
                    break;
            }
        }
        return getCommonElements(foundCars);
    }

    private List<Car> getCommonElements(List<Car> allCars) {
        List<Car> commonCars = new ArrayList<>();
        Set<Car> uniqueCars = new HashSet<>();
        for (Car car : allCars) {
            if (!uniqueCars.add(car)) {
                commonCars.add(car);
            }
        }
        return allCars.equals(new ArrayList<>(uniqueCars)) ? allCars : commonCars;
    }
}
