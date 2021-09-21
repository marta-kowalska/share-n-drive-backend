package com.codecool.sharendrivebackend.model.car;

import com.codecool.sharendrivebackend.model.customer.Customer;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private int seatNumber;

    @Enumerated
    private BodyType bodyType;

    @Column(unique = true)
    private String licencePlate;

    @Enumerated
    private FuelType fuelType;

    private int price;

    private String brand;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

}
