package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.customer.Customer;
import com.codecool.sharendrivebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/share-n-drive")
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/bookings")
    public List<Bookings> getBookingsForCustomer(Authentication authentication) {
        Long id = Long.valueOf(authentication.getName());
        return customerService.getBookingsByCustomerId(id);
    }

    @GetMapping("/customer-details")
    public Customer getCustomerById(Authentication authentication) {
        Long customerId = Long.valueOf(authentication.getName());
        return customerService.findCustomerById(customerId);
    }

    @PostMapping("/book-car")
    public void bookCar(@RequestBody Bookings booking, Authentication authentication) {
        Long customerId = Long.valueOf(authentication.getName());
        customerService.saveBooking(booking, customerId);
    }

    @DeleteMapping("/bookings/{id}")
    public void removeBooking(@PathVariable Long id) {
        customerService.deleteBookingsByCustomerId(id);
    }

    @PostMapping("/register")
    public void register(@RequestBody Customer customer) {
        customerService.registerNewCustomer(customer);
    }


}