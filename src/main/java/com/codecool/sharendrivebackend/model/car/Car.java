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

    private int seatNumber;

    private String color;

    @Column(unique = true)
    private String licencePlate;

    @Basic
    private String fuelType;
    @Transient
    private FuelType fuelTypeEnum;

    @Basic
    private String carType;
    @Transient
    private CarType carTypeEnum;

    @Basic
    private String bodyType;
    @Transient
    private BodyType bodyTypeEnum;

    @Basic
    private String transmission;
    @Transient
    private Transmission transmissionEnum;

    @PostLoad
    void fillTransient() {
        if (fuelType == null) {
            this.fuelTypeEnum = FuelType.getTypeByName(fuelType);
        }
        if (carType == null) {
            this.carTypeEnum = CarType.getTypeByName(carType);
        }
        if (bodyType == null) {
            this.bodyTypeEnum = BodyType.getTypeByName(bodyType);
        }
        if (transmission == null) {
            this.transmissionEnum = Transmission.getTypeByName(transmission);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (fuelTypeEnum != null) {
            this.fuelType = fuelTypeEnum.getName();
        }
        if (carTypeEnum != null) {
            this.carType = carTypeEnum.getName();
        }
        if (bodyTypeEnum != null) {
            this.bodyType = bodyTypeEnum.getName();
        }
        if (transmission != null) {
            this.transmission = transmissionEnum.getName();
        }
    }

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
