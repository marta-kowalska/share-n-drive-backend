package com.codecool.sharendrivebackend.model.customer;

import lombok.*;
import com.codecool.sharendrivebackend.model.car.Car;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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
}
