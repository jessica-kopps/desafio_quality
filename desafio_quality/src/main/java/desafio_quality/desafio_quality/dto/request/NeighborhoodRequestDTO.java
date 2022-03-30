package desafio_quality.desafio_quality.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodRequestDTO {
    private String name;
    private BigDecimal valueDistrictM2;
}
