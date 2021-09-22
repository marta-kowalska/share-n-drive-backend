package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.FuelType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveOneCar() {
        Car volkswagen = Car.builder()
                .seatNumber(5)
                .fuelType(FuelType.ELECTRIC)
                .brand("Volkswagen")
                .build();

        carRepository.save(volkswagen);
        assertEquals(1, carRepository.findAll().size());
    }

    @Test
    public void theLicencePlatesAreUniqueForEachCar() {
        Car car1 = Car.builder()
                .seatNumber(5)
                .fuelType(FuelType.ELECTRIC)
                .brand("Volkswagen")
                .licencePlate("NNN-111")
                .build();

        carRepository.save(car1);

        Car car2 = Car.builder()
                .seatNumber(2)
                .fuelType(FuelType.DIESEL)
                .brand("BMW")
                .licencePlate("NOM-111")
                .build();

        carRepository.saveAndFlush(car2);
    }
}