package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveOneAddress() {
        Address budapest = Address.builder()
                .city("Budapest")
                .street("Nagymező street 44")
                .zipCode(1061)
                .build();

        addressRepository.save(budapest);
        assertEquals(1, addressRepository.findAll().size());
    }

    @Test
    public void afterSaveACustomerWithAddress_ThatAddressHasTheCorrectCustomerId() {

        Address address = Address.builder()
                .city("Budapest")
                .street("Nagymező street 44")
                .zipCode(1061)
                .build();

        Customer customer = Customer.builder()
                .firstName("Feri")
                .lastName("Karpati")
                .address(address)
                .build();

        address.setCustomer(customer);
        customerRepository.save(customer);
        assertEquals(address.getCustomer().getId(), customer.getId());
    }
}