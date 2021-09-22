package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveCustomerWithoutCarOrAddress() {
        Customer peter = Customer.builder()
                .firstName("Peter")
                .lastName("Graham")
                .build();

        customerRepository.save(peter);

        assertEquals(1, customerRepository.findAll().size());

        List<Customer> customers = customerRepository.findAll();
        assertTrue(customers.stream().anyMatch(customer -> customer.getAddress() == null));
        assertTrue(customers.stream().anyMatch(customer -> customer.getCars().isEmpty()));
    }

    @Test
    public void saveCustomerWithAddressOrWithCars() {
        Customer peter = Customer.builder()
                .firstName("Peter")
                .lastName("Graham")
                .address(Address.builder().address("address st. 1").build())
                .build();

        Customer jacob = Customer.builder()
                .firstName("Jacob")
                .lastName("Graham")
                .car(Car.builder().build())
                .build();

        customerRepository.save(peter);
        customerRepository.save(jacob);

        assertTrue(customerRepository.findAll().stream().anyMatch(customer -> customer.getAddress() == null));
        assertTrue(customerRepository.findAll().stream().anyMatch(customer -> customer.getCars().isEmpty()));
    }
}