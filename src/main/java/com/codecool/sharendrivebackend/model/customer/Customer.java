package com.codecool.sharendrivebackend.model.customer;

import com.codecool.sharendrivebackend.model.address.Address;
import com.codecool.sharendrivebackend.model.bookings.Bookings;
import com.codecool.sharendrivebackend.model.car.Car;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
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
    private String phone;
    private String email;

    @NotEmpty
    @Column(unique=true, nullable=false)
    private String username;

    @NotEmpty
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Address address;

    @Singular
    @OneToMany(mappedBy = "customer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Bookings> bookings;
}
