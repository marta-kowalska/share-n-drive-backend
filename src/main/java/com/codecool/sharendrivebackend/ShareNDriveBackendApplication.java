package com.codecool.sharendrivebackend;

import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.car.BodyType;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.FuelType;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ShareNDriveBackendApplication {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShareNDriveBackendApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Car bmw = Car.builder()
                    .bodyType(BodyType.SEDAN)
                    .brand("BMW")
                    .fuelType(FuelType.ELECTRIC)
                    .build();

            Address address = Address.builder()
                    .city("Budapest")
                    .zipCode(1111)
                    .address("Nagymezo st 42")
                    .build();

            Customer customer = Customer.builder()
                    .firstName("Marta")
                    .lastName("Kowalska")
                    .car(bmw)
                    .address(address)
                    .build();

            bmw.setCustomer(customer);
            address.setCustomer(customer);
            customerRepository.save(customer);
        };
    }
}
