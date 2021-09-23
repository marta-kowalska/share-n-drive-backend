package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.BookingsRepository;
import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.model.car.BodyType;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.CarType;
import com.codecool.sharendrivebackend.model.car.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CarService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final CarRepository carRepository;
    private final BookingsRepository bookingsRepository;

    @Autowired
    public CarService(CarRepository carRepository, BookingsRepository bookingsRepository) {
        this.carRepository = carRepository;
        this.bookingsRepository = bookingsRepository;
    }

    public List<Car> getFeaturedCars() {
        return null;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getFilteredCars(Map<String, String> params) {
        List<Car> foundCars = new ArrayList<>();
        Map<String, List<String>> checkedParams = getUniqueParams(params);
        for (String key : params.keySet()) {
            switch (key) {
                case "brand":
                    foundCars.addAll(carRepository.findCarsByStringValue(checkedParams.get(key), "brand"));
                    break;
                case "color":
                    foundCars.addAll(carRepository.findCarsByStringValue(checkedParams.get(key), "color"));
                    break;
                case "price":
                    int maxPrice = Integer.parseInt((checkedParams.get(key)).get(0));
                    foundCars.addAll(carRepository.findByPriceLessThanEqual(maxPrice));
                    break;
                case "seatNumber":
                    int minSeatNumber = Integer.parseInt((checkedParams.get(key)).get(0));
                    foundCars.addAll(carRepository.findBySeatNumberGreaterThanEqual(minSeatNumber));
                    break;
                case "from":
                    LocalDate from = LocalDate.parse(params.get("from"), formatter);
                    LocalDate to = LocalDate.parse(params.get("to"), formatter);
                    foundCars.addAll(bookingsRepository.getCarsByDates(from, to));
                    break;
                case "fuelType":
                    foundCars.addAll(carRepository.findCarsByFuelType(checkedParams.get(key)));
                    break;
                case "bodyType":
                    foundCars.addAll(carRepository.findCarsByBodyType(checkedParams.get(key)));
                    break;
                case "carType":
                    foundCars.addAll(carRepository.findCarsByCarType(checkedParams.get(key)));
                    break;
            }
        }
        return (checkedParams.keySet().size() == 1 ? foundCars : getCommonElements(foundCars));
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car) {
        return 0;
    } //TODO: think about location for this method

    private Map<String, List<String>> getUniqueParams(Map<String, String> params) {
        Map<String, List<String>> checkedParams = new HashMap<>();
        for (String param : params.keySet()) {
            List<String> values = Arrays.stream(params.get(param).split(","))
                .distinct()
                .collect(Collectors.toList());
            checkedParams.put(param, values);
        }
        checkFromToParams(checkedParams);
        return checkedParams;
    }

    private void checkFromToParams(Map<String, List<String>> checkedParams) {
        if (checkedParams.containsKey("from") && !checkedParams.containsKey("to")) {
            checkedParams.remove("from");
        }
    }

    private List<Car> getCommonElements(List<Car> allCars) {
        List<Car> commonCars = new ArrayList<>();
        Set<Car> uniqueCars = new HashSet<>();
        for (Car car : allCars) {
            if (!uniqueCars.add(car)) {
                commonCars.add(car);
            }
        }
        return commonCars;
    }

    public void saveCarToRent(Car car) {
        carRepository.save(car);
    }

    public List<FuelType> getFuelTypes() {
        return Arrays.asList(FuelType.values());
    }

    public List<BodyType> getBodyTypes() {
        return Arrays.asList(BodyType.values());
    }

    public List<CarType> getCarTypes() {
        return Arrays.asList(CarType.values());
    }
}
