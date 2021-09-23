package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {

    List<Car> findByPriceLessThanEqual(int price);

    List<Car> findBySeatNumberGreaterThanEqual(int seatNumber);
}
