package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.customer.Customer;
import com.codecool.sharendrivebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/bookings/{customerId}")
    public List<Bookings> getBookingsForCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getBookingsByCustomerId(customerId);
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping("/book-car")
    public void bookCar(@RequestBody Bookings booking) {
        customerService.saveBooking(booking);
    }

    @GetMapping("/getFirstCustomer")
    public Customer getCustomer() {
        return customerService.getFirstCustomer();
    }


}