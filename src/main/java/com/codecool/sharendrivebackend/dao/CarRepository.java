package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {

    List<Car> findByPriceLessThanEqual(int price);

    List<Car> findBySeatNumberGreaterThanEqual(int seatNumber);

    List<Car> findByDoorsEquals(int doors);

    @Query("select DISTINCT c.color from Car c")
    List<String> getColors();

    @Query("select DISTINCT c.doors from Car c")
    List<String> getDoors();

    @Query("select DISTINCT c.brand from Car c")
    List<String> getBrands();
}
