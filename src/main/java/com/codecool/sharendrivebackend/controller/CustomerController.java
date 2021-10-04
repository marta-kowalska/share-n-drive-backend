package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.customer.Customer;
import com.codecool.sharendrivebackend.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/share-n-drive")
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {

    private final CustomerService customerService;

    public static Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
    public Customer getCustomer(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            logger.warn(String.format("Header '%s' = %s", key, value));
        });
        return customerService.getFirstCustomer();
    }


}