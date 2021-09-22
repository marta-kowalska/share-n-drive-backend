package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
}