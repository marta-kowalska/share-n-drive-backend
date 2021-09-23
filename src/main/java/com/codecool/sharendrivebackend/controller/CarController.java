package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.car.BodyType;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codecool.sharendrivebackend.service.CarService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/share-n-drive")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/featured")
    public List<Car> getFeaturedCars() {
        return carService.getFeaturedCars();
    }

    @GetMapping("/filter/all")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(value="/filter", method = RequestMethod.GET)
    public List<Car> getParams(@RequestParam Map<String, String> params ) {
        return carService.getFilteredCars(params);
    }

//    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add-car")
    public void addCar(@RequestBody Car car){
        carService.saveCarToRent(car);
    }

    @GetMapping("/fuelTypes")
    public List<FuelType> getFuelTypes() {
        return carService.getFuelTypes();
    }

    @GetMapping("/bodyTypes")
    public List<BodyType> getBodyTypes() {
        return carService.getBodyTypes();
    }


}
