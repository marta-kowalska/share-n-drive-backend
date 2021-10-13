package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.BookingsRepository;
import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.car.*;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CarService {

    private static final List<String> FILTER_CRITERIA = new ArrayList<>(List.of("brand", "color", "fuelType",
        "doors", "bodyType", "carType", "transmission"));

    private final CarRepository carRepository;
    private final BookingsRepository bookingsRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CarService(CarRepository carRepository, BookingsRepository bookingsRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.bookingsRepository = bookingsRepository;
        this.customerRepository = customerRepository;
    }

    public List<Car> getAllCars(String customer) {
        return filterByCustomer(customer, carRepository.findAll());
    }

    public List<Car> getFilteredCars(Map<String, String> params) {
        Map<String, List<String>> checkedParams = getCheckedParams(params);
        List<Car> carsByCriteria = carRepository.findCarsByCriteria(checkedParams);
        checkFromToParams(params);
        if (params.containsKey("rentFrom")) {
            List<Car> carsByDate = filterByDate(params);
            carsByCriteria.addAll(carsByDate);
            carsByCriteria = getCommonElements(carsByCriteria);
        }
        return filterByCustomer(params.get("customerId"), carsByCriteria);
    }

    private List<Car> filterByDate(Map<String, String> params) {
        LocalDate from = LocalDate.parse(params.get("rentFrom"));
        LocalDate to = LocalDate.parse(params.get("rentTo"));
        return bookingsRepository.getCarsByDates(from, to);
    }

    private List<Car> filterByCustomer(String user, List<Car> filteredCars) {
        if (!user.equals("anonymousUser")) {
            Long customerId = Long.valueOf(user);
            Customer c = customerRepository.findById(customerId).get();
            for (Car car : c.getCars()) {
                filteredCars.remove(car);
            }
        }
        return filteredCars;
    }

    public int calculatePriceForRentTime(LocalDate from, LocalDate to, Car car) {
        int days = (int) ChronoUnit.DAYS.between(from, to);
        return car.getPrice() * days;
    }

    private Map<String, List<String>> getCheckedParams(Map<String, String> params) {
        Map<String, List<String>> checkedParams = new HashMap<>();
        for (String param : params.keySet()) {
            if(FILTER_CRITERIA.contains(param)){
            List<String> values = Arrays.stream(params.get(param).split(","))
                .distinct()
                .collect(Collectors.toList());
            checkedParams.put(param, values);
            }
        }
        return checkedParams;
    }

    private void checkFromToParams(Map<String, String> checkedParams) {
        if (checkedParams.containsKey("rentFrom") && !checkedParams.containsKey("rentTo")) {
            checkedParams.remove("rentFrom");
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
