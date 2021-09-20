package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarType type;
}
