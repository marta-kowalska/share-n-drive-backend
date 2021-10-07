package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    @Query("SELECT distinct c from Car c where not exists (SELECT 1 from Bookings b " +
            "WHERE c.id = b.car.id and (b.rentFrom < :to and b.rentTo > :from))")
    List<Car> getCarsByDates(@Param("from") LocalDate from, @Param("to") LocalDate to);

    List<Bookings> findAllByCustomer(Customer customer);

    @Query("SELECT distinct b from Bookings b where b.car in (SELECT c from Car c where c.customer = :customer)")
    List<Bookings> findAllBookingsForCustomerCars(@Param("customer") Customer customer);
}