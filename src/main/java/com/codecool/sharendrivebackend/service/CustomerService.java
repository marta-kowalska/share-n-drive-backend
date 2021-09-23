package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.BookingsRepository;
import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingsRepository bookingsRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookingsRepository bookingsRepository) {
        this.customerRepository = customerRepository;
        this.bookingsRepository = bookingsRepository;
    }

    public List<Bookings> getBookingsByCustomerId(Long id){
        Customer customer = customerRepository.findById(id).get();
        return bookingsRepository.findAllByCustomer(customer);
    }
}
