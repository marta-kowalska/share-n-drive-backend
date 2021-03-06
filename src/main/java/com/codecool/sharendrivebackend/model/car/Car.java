package com.codecool.sharendrivebackend.model.car;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(value = { "bookings" })
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAR_GENERATOR")
    @SequenceGenerator(name="CAR_GENERATOR")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private int seatNumber;

    private String color;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(unique = true)
    private String licencePlate;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private int price;

    private int doors;

    private double rating;

    private String brand;
    private String title;


    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Singular
    private Set<Bookings> bookings;


}
