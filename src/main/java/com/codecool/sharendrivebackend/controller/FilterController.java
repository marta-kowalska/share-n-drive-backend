package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codecool.sharendrivebackend.service.CarService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/share-n-drive")
public class FilterController {

    private final CarService carService;

    @Autowired
    public FilterController(CarService carService){
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
        return carService.getFilteredCars2(params);
    }


}
