package com.codecool.sharendrivebackend.model.bookings;

import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Bookings {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate rentFrom;

    private LocalDate rentTo;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;
}


