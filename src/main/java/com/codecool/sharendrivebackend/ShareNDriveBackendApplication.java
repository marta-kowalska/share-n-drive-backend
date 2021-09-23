package com.codecool.sharendrivebackend;

import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.car.BodyType;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.CarType;
import com.codecool.sharendrivebackend.model.car.FuelType;
import com.codecool.sharendrivebackend.model.customer.Customer;
import com.codecool.sharendrivebackend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ShareNDriveBackendApplication {

    @Autowired
    private CarRepository carRepository;

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
                    .color("Black")
                    .carType(CarType.ECO)
                    .licencePlate("AAA-111")
                    .seatNumber(4)
                    .fuelType(FuelType.ELECTRIC)
                    .build();

            Car car1 = Car.builder()
                    .bodyType(BodyType.CONVERTIBLE)
                    .brand("Audi")
                    .color("White")
                    .carType(CarType.SELF_DRIVING)
                    .licencePlate("BBB-111")
                    .seatNumber(2)
                    .fuelType(FuelType.GASOLINE)
                    .build();

            Car car2 = Car.builder()
                    .bodyType(BodyType.SEDAN)
                    .brand("Volkswagen")
                    .color("Silver")
                    .carType(CarType.ECO)
                    .licencePlate("ABA-111")
                    .seatNumber(5)
                    .fuelType(FuelType.GASOLINE)
                    .build();

            Address address1 = Address.builder().city("Budapest").zipCode(1111).street("Nagymezo").house("44").build();

            Customer customer = Customer.builder()
                    .firstName("Marta")
                    .lastName("Kowalska")
                    .userName("Marta")
                    .email("Marta@gmail.com")
                    .phone("+36-30-111-111")
                    .address(address1)
                    .car(bmw)
                    .car(car1)
                    .car(car2)
                    .build();

            bmw.setCustomer(customer);
            car1.setCustomer(customer);
            car2.setCustomer(customer);
            address1.setCustomer(customer);

            customerRepository.save(customer);
        };
    }
}
