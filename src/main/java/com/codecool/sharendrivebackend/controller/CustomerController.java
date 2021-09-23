package com.codecool.sharendrivebackend.controller;

import com.codecool.sharendrivebackend.model.customer.Customer;
import com.codecool.sharendrivebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share-n-drive")
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @RequestMapping(value="/user", method = RequestMethod.GET)
    public Customer getFirstUser() {
        return customerService.getCustomer();
    }


}
