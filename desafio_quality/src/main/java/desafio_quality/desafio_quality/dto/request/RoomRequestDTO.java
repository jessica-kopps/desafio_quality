package desafio_quality.desafio_quality.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDTO {

    @NotBlank(message = "O nome do cômodo não pode estar vazio.")
    @Pattern(regexp = "[A-Z]\\w.+", message = "O nome do cômodo deve começar com letra maiúscula.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder a 30 caracteres.")
    private String name;

    @NotNull(message = "O campo do comprimento não pode estar vazio.")
    @Max(value = 33, message = "O comprimento máximo permitido do cômodo é de 33 metros.")
    private Double length;

    @NotNull(message = "O campo da largura não pode estar vazio.")
    @Max(value = 25, message = "A largura máxima permitido do cômodo é de 25 metros.")
    private Double width;

}
