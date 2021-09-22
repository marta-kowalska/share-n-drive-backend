package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
}