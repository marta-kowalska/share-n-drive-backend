package com.codecool.sharendrivebackend.model.car;

import com.codecool.sharendrivebackend.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    private String color;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Column(unique = true)
    private String licencePlate;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private int price;

    private String brand;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;

}
