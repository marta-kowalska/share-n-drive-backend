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

    private static final Set<String> VALID_FILTER_CRITERIA = new HashSet<>(List.of("brand", "plate", "type", "price"));

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
        Map<String, List<String>> checkedParams = createCheckedParamMap(params);
        return carRepository.findCarsByCriteria(checkedParams);
    }

    private Map<String, List<String>> createCheckedParamMap(Map<String, String> params) {
        Map<String, List<String>> checkedParams = new HashMap<>();

        for(String param : params.keySet()){
            if(isCriteriaFilterCorrect(param)){
                List<String> values = Arrays.stream(params.get(param).split(","))
                    .distinct()
                    .collect(Collectors.toList());
                checkedParams.put(param, values);
            }
        }
        return checkedParams;
    }

    private boolean isCriteriaFilterCorrect(String param) {
        return VALID_FILTER_CRITERIA.contains(param.toLowerCase());
    }
}
