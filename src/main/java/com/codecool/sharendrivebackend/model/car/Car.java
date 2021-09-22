package com.codecool.sharendrivebackend.model.car;

import com.codecool.sharendrivebackend.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"customer", "licencePlate"})
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private int seatNumber;

    private String color;

    @Enumerated
    private BodyType bodyType;

    @Column(unique = true)
    private String licencePlate;

//    @ManyToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
//    @Singular
//    @EqualsAndHashCode.Exclude
//    private List<RentCalendar> calendars;

    @Enumerated
    private FuelType fuelType;

    private int price;

    private String brand;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

}
