package desafio_quality.desafio_quality.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
