package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findById(Long id);

    Optional<Object> findByUsername(String username);

    @Query("select cu.id from Customer cu where cu.username = :username")
    Long getIdByCustomerName(@Param("username") String username);
}
