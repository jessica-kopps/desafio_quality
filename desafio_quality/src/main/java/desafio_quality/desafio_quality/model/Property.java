package desafio_quality.desafio_quality.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    private Long id;
    private String name;
    private List<Room> rooms;
    private Neighborhood neighborhood;

}
