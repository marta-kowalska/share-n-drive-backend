package com.codecool.sharendrivebackend.model.address;

import com.codecool.sharendrivebackend.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"customer", "address"})
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    private int zipCode;

    private String city;

    private String street;

    private String house;
}
