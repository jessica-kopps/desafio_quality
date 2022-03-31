package desafio_quality.desafio_quality.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodRequestDTO {

    @NotBlank(message = "O nome do bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder a 45 caracteres.")
    private String name;

    @NotNull(message = "O valor do metro quadrado não pode estar vazio.")
    @Digits(integer = 13, fraction = 2, message = "A valor do metro quadrado deve ser inferior a 13 dígitos.")
    private BigDecimal valueDistrictM2;
}
