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
                    .brand("Tesla")
                    .title("model s")
                    .color("White")
                    .carType(CarType.SELF_DRIVING)
                    .licencePlate("MAR-TA1")
                    .seatNumber(7)
                    .fuelType(FuelType.ELECTRIC)
                    .price(18000)
                    .build();

            Car car1 = Car.builder()
                    .bodyType(BodyType.STATION_WAGON)
                    .brand("Audi")
                    .title("RS6")
                    .color("Silver")
                    .carType(CarType.GAS_GUZZLER)
                    .licencePlate("MAR-TA2")
                    .seatNumber(5)
                    .fuelType(FuelType.GASOLINE)
                    .price(37000)
                    .build();

            Car car2 = Car.builder()
                    .bodyType(BodyType.SEDAN)
                    .brand("BMW")
                    .title("760Li")
                    .color("Black")
                    .carType(CarType.GAS_GUZZLER)
                    .licencePlate("MAR-TA3")
                    .seatNumber(4)
                    .fuelType(FuelType.GASOLINE)
                    .price(32000)
                    .build();
            Car car4 = Car.builder()
                    .bodyType(BodyType.SEDAN)
                    .brand("Tesla")
                    .title("model y")
                    .color("white")
                    .carType(CarType.SELF_DRIVING)
                    .licencePlate("AAA-111")
                    .seatNumber(7)
                    .fuelType(FuelType.ELECTRIC)
                    .price(15000)
                    .build();

            Car car5 = Car.builder()
                    .bodyType(BodyType.CONVERTIBLE)
                    .brand("Mercedes")
                    .title("AMG GT")
                    .color("silver")
                    .carType(CarType.RACE_CAR)
                    .licencePlate("MAR-TA4")
                    .seatNumber(7)
                    .fuelType(FuelType.GASOLINE)
                    .price(56000)
                    .build();

            Car car3 = Car.builder()
                    .bodyType(BodyType.SEDAN)
                    .brand("Mercedes")
                    .title("S65 AMG")
                    .color("silver")
                    .carType(CarType.LUXURY)
                    .licencePlate("BBB-111")
                    .seatNumber(5)
                    .fuelType(FuelType.GASOLINE)
                    .price(42000)
                    .build();

            Car car6 = Car.builder()
                    .bodyType(BodyType.HATCHBACK)
                    .brand("Renault")
                    .title("clio")
                    .color("silver")
                    .carType(CarType.FUN)
                    .licencePlate("xxx-xxx")
                    .seatNumber(5)
                    .fuelType(FuelType.GASOLINE)
                    .price(8000)
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
            carRepository.save(car3);
            carRepository.save(car4);
            carRepository.save(car5);
            carRepository.save(car6);
            customerRepository.save(customer);
        };
    }
}
