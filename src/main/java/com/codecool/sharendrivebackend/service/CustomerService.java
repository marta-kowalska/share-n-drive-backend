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
        Customer customer = findCustomerById(id);
        return bookingsRepository.findAllByCustomer(customer);
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id).get();
    }

    public void saveBooking(Bookings booking) {
        bookingsRepository.save(booking);
    }

    public void deleteBookingsByCustomerId(Long id) {
        bookingsRepository.deleteById(id);
    }

    public String getCustomerIdByUsername(String username) {
        return customerRepository.getIdByCustomerName(username).toString();
    }
}
