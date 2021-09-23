package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c.customer from Car c")
    List<Customer> findAllCustomerByGivenCarName(@Param("name") String carName);
}
