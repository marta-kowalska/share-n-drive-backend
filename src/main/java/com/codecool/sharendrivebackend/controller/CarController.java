package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.car.*;
import com.codecool.sharendrivebackend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/share-n-drive")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/filter/all")
    public List<Car> getAllCars() {
        String customer = SecurityContextHolder.getContext().getAuthentication().getName();
        return carService.getAllCars(customer);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Car> getParams(@RequestParam Map<String, String> params) {
        String customer = SecurityContextHolder.getContext().getAuthentication().getName();
        params.put("customerId", customer);
        return carService.getFilteredCars(params);
    }

    @PostMapping("/add-car")
    public void addCar(@RequestBody Car car, Authentication authentication) {
        Long customerId = Long.valueOf(authentication.getName());
        carService.saveCarToRent(car, customerId);
    }

    @GetMapping("/fuelTypes")
    public List<FuelType> getFuelTypes() {
        return carService.getFuelTypes();
    }

    @GetMapping("/bodyTypes")
    public List<BodyType> getBodyTypes() {
        return carService.getBodyTypes();
    }

    @GetMapping("/carTypes")
    public List<CarType> getCarTypes() {
        return carService.getCarTypes();
    }

    @GetMapping("/colors")
    public List<String> getCarColors() {
        return carService.getColors();
    }

    @GetMapping("/transmissionTypes")
    public List<Transmission> getTransmissionTypes() {
        return carService.getTransmissionTypes();
    }

    @GetMapping("/DoorTypes")
    public List<String> getDoorTypes() {
        return carService.getDoorTypes();
    }

    @GetMapping("/brands")
    public List<String> getBrands() {
        return carService.getBrands();
    }

    @DeleteMapping("/remove-car/{id}")
    public void deleteCar(@PathVariable Long id, Authentication authentication) {
        carService.deleteCar(id, authentication.getName());
    }
}
