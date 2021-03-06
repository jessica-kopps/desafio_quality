package desafio_quality.desafio_quality.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyPriceResponseDTO {

    private PropertyResponseDTO property;
    private BigDecimal totalPrice;

}
