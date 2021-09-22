package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.RentTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentTimeRepository extends JpaRepository<RentTime, Long> {
}
