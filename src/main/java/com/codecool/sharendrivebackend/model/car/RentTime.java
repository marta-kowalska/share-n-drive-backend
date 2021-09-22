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
public class RentTime {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate rentFrom;

    private LocalDate rentTo;

    @ManyToMany(mappedBy = "rentTimes", cascade = CascadeType.PERSIST)
    @Singular
    private List<Car> cars;
}
