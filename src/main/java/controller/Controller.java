package controller;

import model.Car;
import model.CarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CarService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/share-n-drive")
public class Controller {

    private final CarService carService;

    @Autowired
    public Controller(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/")
    public List<Car> getMainPage() {
        return carService.getFeaturedCars();
    }

    @GetMapping("/filter?from={from}&to={to}")
    public List<Car> getCarsByDate(@PathVariable("from")LocalDate from, @PathVariable("to")LocalDate to) {
        return carService.getAvailableCarsByDate(from, to);
    }

    @GetMapping("/filter?type={types}")
    public List<Car> getCarsByType(@PathVariable("types") List<CarType> types) {
        return carService.getAvailableCarsByType(types);
    }

}
