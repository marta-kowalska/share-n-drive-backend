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

import java.util.Arrays;

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
            Car volkswagen = Car.builder()
                    .brand("Volkswagen")
                    .licencePlate("AAA-111")
                    .seatNumber(5)
                    .fuelType(FuelType.GASOLINE)
                    .bodyType(BodyType.SEDAN)
                    .build();

            Car bmw = Car.builder()
                    .brand("BMW")
                    .licencePlate("BBB-222")
                    .seatNumber(7)
                    .fuelType(FuelType.DIESEL)
                    .bodyType(BodyType.STATION_WAGON)
                    .build();

            Car volkswagenElectric = Car.builder()
                    .brand("Volkswagen")
                    .licencePlate("CCC-222")
                    .seatNumber(2)
                    .fuelType(FuelType.ELECTRIC)
                    .bodyType(BodyType.CONVERTIBLE)
                    .build();

            Car mercedes = Car.builder()
                    .brand("Mercedes-Benz")
                    .licencePlate("BBB-111")
                    .seatNumber(5)
                    .fuelType(FuelType.GASOLINE)
                    .bodyType(BodyType.COUPE)
                    .build();

            Address budapest1 = Address.builder()
                    .city("Budapest")
                    .zipCode(1111)
                    .address("Address street 1")
                    .build();

            Address budapest2 = Address.builder()
                    .city("Budapest")
                    .zipCode(1221)
                    .address("Address street 2")
                    .build();

            Customer one = Customer.builder()
                    .firstName("Mr")
                    .lastName("One")
                    .address(budapest1)
                    .car(volkswagen)
                    .car(volkswagenElectric)
                    .rentedCar(bmw)
                    .build();

            Customer two = Customer.builder()
                    .firstName("Mr")
                    .lastName("Two")
                    .address(budapest2)
                    .car(mercedes)
                    .car(bmw)
                    .rentedCar(volkswagen)
                    .rentedCar(volkswagenElectric)
                    .build();

            budapest1.setCustomer(one);
            budapest2.setCustomer(two);

            volkswagen.setCustomer(one);
            volkswagen.setWhichCustomerRent(two);
            volkswagenElectric.setCustomer(one);
            volkswagenElectric.setWhichCustomerRent(two);
            bmw.setCustomer(two);
            bmw.setWhichCustomerRent(one);
            mercedes.setCustomer(two);

            customerRepository.saveAll(Arrays.asList(one, two));
        };
    }
}
