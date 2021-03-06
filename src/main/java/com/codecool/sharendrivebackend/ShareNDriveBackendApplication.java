package com.codecool.sharendrivebackend;

import com.codecool.sharendrivebackend.dao.CarRepository;
import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.car.*;
import com.codecool.sharendrivebackend.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class ShareNDriveBackendApplication {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


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
                .title("Model S")
                .color("White")
                .carType(CarType.SELF_DRIVING)
                .licencePlate("MARTA-1")
                .seatNumber(7)
                .transmission(Transmission.AUTOMATIC)
                .doors(4)
                .rating(7.5)
                .fuelType(FuelType.ELECTRIC)
                .price(18000)
                .build();

            Car car1 = Car.builder()
                .bodyType(BodyType.STATION_WAGON)
                .brand("Audi")
                .title("RS6")
                .color("Silver")
                .carType(CarType.GAS_GUZZLER)
                .licencePlate("MARTA-2")
                .seatNumber(5)
                .transmission(Transmission.AUTOMATIC)
                .doors(5)
                .rating(8.5)
                .fuelType(FuelType.GASOLINE)
                .price(37000)
                .build();

            Car car2 = Car.builder()
                .bodyType(BodyType.SEDAN)
                .brand("BMW")
                .title("760Li")
                .color("Black")
                .carType(CarType.GAS_GUZZLER)
                .licencePlate("MARTA-3")
                .seatNumber(4)
                .transmission(Transmission.AUTOMATIC)
                .doors(5)
                .rating(8.5)
                .fuelType(FuelType.GASOLINE)
                .price(32000)
                .build();
            Car car4 = Car.builder()
                .bodyType(BodyType.SEDAN)
                .brand("Tesla")
                .title("Model X")
                .color("White")
                .carType(CarType.SELF_DRIVING)
                .licencePlate("AGA-111")
                .seatNumber(7)
                .transmission(Transmission.AUTOMATIC)
                .doors(6)
                .rating(8.5)
                .fuelType(FuelType.ELECTRIC)
                .price(15000)
                .build();

            Car car5 = Car.builder()
                .bodyType(BodyType.CONVERTIBLE)
                .brand("Mercedes")
                .title("AMG GT")
                .color("Silver")
                .carType(CarType.RACE_CAR)
                .licencePlate("MARTA-4")
                .seatNumber(7)
                .transmission(Transmission.AUTOMATIC)
                .doors(2)
                .rating(9.7)
                .fuelType(FuelType.GASOLINE)
                .price(56000)
                .build();

            Car car3 = Car.builder()
                .bodyType(BodyType.SEDAN)
                .brand("Mercedes")
                .title("S65 AMG")
                .color("Silver")
                .carType(CarType.LUXURY)
                .licencePlate("BFG-567")
                .seatNumber(5)
                .transmission(Transmission.AUTOMATIC)
                .doors(4)
                .rating(8.6)
                .fuelType(FuelType.GASOLINE)
                .price(42000)
                .build();

            Car car6 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Renault")
                .title("Clio")
                .color("Silver")
                .carType(CarType.FUN)
                .licencePlate("xxx-xxx")
                .seatNumber(5)
                .transmission(Transmission.MANUAL)
                .doors(5)
                .rating(9.5)
                .fuelType(FuelType.GASOLINE)
                .price(8000)
                .build();

            Car car7 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Dacia")
                .title("Duster")
                .color("orange")
                .carType(CarType.ECO)
                .licencePlate("DFD-323")
                .seatNumber(5)
                .transmission(Transmission.MANUAL)
                .doors(5)
                .rating(4.3)
                .fuelType(FuelType.GASOLINE)
                .price(10000)
                .build();
            Car car8 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Suzuki")
                .title("Swift")
                .color("Blue")
                .carType(CarType.ECO)
                .licencePlate("DFG-121")
                .seatNumber(5)
                .transmission(Transmission.MANUAL)
                .doors(3)
                .rating(4.6)
                .fuelType(FuelType.DIESEL)
                .price(7000)
                .build();
            Car car9 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Skoda")
                .title("Octavia")
                .color("Blue")
                .carType(CarType.FUN)
                .licencePlate("SFD-689")
                .seatNumber(5)
                .transmission(Transmission.AUTOMATIC)
                .doors(4)
                .rating(6.7)
                .fuelType(FuelType.GAS)
                .price(15000)
                .build();
            Car car10 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Ford")
                .title("Fiesta")
                .color("Red")
                .carType(CarType.FUN)
                .licencePlate("FSA-435")
                .seatNumber(5)
                .transmission(Transmission.MANUAL)
                .doors(3)
                .rating(7.4)
                .fuelType(FuelType.DIESEL)
                .price(12000)
                .build();
            Car car11 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Nissan")
                .title("Leaf")
                .color("Red")
                .carType(CarType.SELF_DRIVING)
                .licencePlate("DFW-345")
                .seatNumber(4)
                .transmission(Transmission.AUTOMATIC)
                .doors(4)
                .rating(7.5)
                .fuelType(FuelType.ELECTRIC)
                .price(17000)
                .build();
            Car car12 = Car.builder()
                .bodyType(BodyType.HATCHBACK)
                .brand("Peugeot")
                .title("Teepee")
                .color("Blue")
                .carType(CarType.ECO)
                .licencePlate("TRE-345")
                .seatNumber(4)
                .transmission(Transmission.MANUAL)
                .doors(5)
                .rating(6.5)
                .fuelType(FuelType.DIESEL)
                .price(9000)
                .build();


            Address address1 = Address.builder().city("Budapest").zipCode(1111).street("Nagymezo").house("44").build();
            Address address2 = Address.builder().city("Debrecen").zipCode(1111).street("Nagymezo").house("44").build();

            Customer customer = Customer.builder()
                    .firstName("Marta")
                    .lastName("Kowalska")
                    .username("Marta")
                    .avatar(2)
                .password(passwordEncoder.encode("password"))
                .roles(Arrays.asList("CUSTOMER"))
                .email("Marta@gmail.com")
                .phone("+36-30-111-111")
                .address(address1)
                .car(bmw)
                .car(car1)
                .car(car2)
                .build();

            Customer customer2 = Customer.builder()
                    .firstName("Norbi")
                    .lastName("Ruff")
                    .username("Norbi")
                    .avatar(3)
                .password(passwordEncoder.encode("password"))
                .roles(Arrays.asList("CUSTOMER"))
                .email("norbi@gmail.com")
                .phone("+36-30-111-111")
                .address(address2)
                .car(car11)
                .car(car12)
                .build();

            bmw.setCustomer(customer);
            car1.setCustomer(customer);
            car2.setCustomer(customer);
            car11.setCustomer(customer2);
            car12.setCustomer(customer2);
            address1.setCustomer(customer);
            address2.setCustomer(customer2);
            carRepository.save(car3);
            carRepository.save(car4);
            carRepository.save(car5);
            carRepository.save(car6);
            carRepository.save(car7);
            carRepository.save(car8);
            carRepository.save(car9);
            carRepository.save(car10);
//            carRepository.save(car11);
//            carRepository.save(car12);
            customerRepository.save(customer);
            customerRepository.save(customer2);
        };
    }
}