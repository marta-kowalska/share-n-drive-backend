package com.codecool.sharendrivebackend.model.car;

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

    private Long customerId;

    private Long carId;
}
