package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.BookingsRepository;
import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.car.*;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CarService {

    private final CarRepository carRepository;
    private final BookingsRepository bookingsRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CarService(CarRepository carRepository, BookingsRepository bookingsRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.bookingsRepository = bookingsRepository;
        this.customerRepository = customerRepository;
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
        for (String key : checkedParams.keySet()) {
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
                case "rentFrom":
                    LocalDate from = LocalDate.parse(params.get("rentFrom"));
                    LocalDate to = LocalDate.parse(params.get("rentTo"));
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
                case "doors":
                    foundCars.addAll(carRepository.findByDoorsEquals(Integer.parseInt((checkedParams.get(key)).get(0))));
                    break;
                case "transmission":
                    foundCars.addAll(carRepository.findCarsByTransmissionType(checkedParams.get(key)));
                    break;
            }
        }
        return (checkedParams.keySet().size() == 1 ? foundCars : getCommonElements(foundCars));
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car) {
        int days = (int) ChronoUnit.DAYS.between(from, to);
        return car.getPrice() * days;
    }

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
        if (checkedParams.containsKey("rentFrom") && !checkedParams.containsKey("rentTo")) {
            checkedParams.remove("rentFrom");
        }
        if (checkedParams.containsKey("rentTo") && !checkedParams.containsKey("rentFrom")) {
            checkedParams.remove("rentTo");
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

    public void saveCarToRent(Car car, Long customerId) {
        car.setCustomer(customerRepository.getById(customerId));
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

    public List<Transmission> getTransmissionTypes() {
        return Arrays.asList(Transmission.values());
    }

    public List<String> getColors() {
        return carRepository.getColors();
    }

    public List<String> getBrands() {
        return carRepository.getBrands();
    }

    public List<String> getDoorTypes() {
        return carRepository.getDoors();
    }


    public void deleteCar(Long carToDelete, String userId) {
        Long carId = Long.valueOf(carToDelete);
        Customer customerId = customerRepository.getById(Long.valueOf(userId));
        carRepository.removeCarByIdWhereUserIdCorrect(carId, customerId);
    }
}
