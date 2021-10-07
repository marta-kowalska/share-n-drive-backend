package com.codecool.sharendrivebackend.service;

import com.codecool.sharendrivebackend.dao.BookingsRepository;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    public void saveBooking(Bookings booking, Long customerId) {
        LocalDate today = LocalDate.now();

        if (booking.getRentTo() == null || booking.getRentFrom() == null) {
            Bookings newBooking = Bookings.builder()
                    .car(booking.getCar())
                    .rentFrom(today)
                    .rentTo(today)
                    .customer(customerRepository.getById(customerId))
                    .build();
            bookingsRepository.save(newBooking);
        } else {
            booking.setCustomer(customerRepository.getById(customerId));
            bookingsRepository.save(booking);
        }
    }

    public void deleteBookingsByCustomerId(Long id) {
        bookingsRepository.deleteById(id);
    }

    public String getCustomerIdByUsername(String username) {
        return customerRepository.getIdByCustomerName(username).toString();
    }

    public void registerNewCustomer(Customer customer) {
        Address address = Address.builder().city("").zipCode(0).street("").house("").build();
        Customer newCustomer = Customer.builder()
                .firstName("")
                .lastName("")
                .username(customer.getUsername())
                .avatar(customer.getAvatar())
                .password(passwordEncoder.encode(customer.getPassword()))
                .roles(Arrays.asList("CUSTOMER"))
                .email(customer.getEmail())
                .address(address)
                .phone("")
                .build();
        address.setCustomer(newCustomer);
        customerRepository.save(newCustomer);
    }

    public List<Bookings> getAllBookingsForCustomerCars(Customer customer) {
        return bookingsRepository.findAllBookingsForCustomerCars(customer);
    }
}
