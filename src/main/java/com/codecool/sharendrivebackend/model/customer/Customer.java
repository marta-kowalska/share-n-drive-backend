package com.codecool.sharendrivebackend.model.customer;

import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.address.Address;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(value = { "bookings" })
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Singular
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Car> cars;

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Address address;

    @Singular
    @OneToMany(mappedBy = "customer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Bookings> bookings;
}
