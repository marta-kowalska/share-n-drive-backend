package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.BookingsRepository;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingsRepository bookingsRepository;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookingsRepository bookingsRepository) {
        this.customerRepository = customerRepository;
        this.bookingsRepository = bookingsRepository;
    }

    public List<Bookings> getBookingsByCustomerId(Long id){
        Customer customer = findCustomerById(id);
        return bookingsRepository.findAllByCustomer(customer);
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id).get();
    }

    public void saveBooking(Bookings booking) {
        bookingsRepository.save(booking);
    }

    public Customer getFirstCustomer() {
        return customerRepository.findAll().get(0);
    }

    public void registerNewCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
                .firstName("")
                .lastName("")
                .username(customer.getUsername())
                .password(passwordEncoder.encode(customer.getPassword()))
                .roles(Arrays.asList("CUSTOMER"))
                .email(customer.getEmail())
                .phone("")
                .build();
        customerRepository.save(newCustomer);
    }
}
