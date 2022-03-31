package desafio_quality.desafio_quality.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequestDTO {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "[A-Z]\\w.+", message = "O nome da propriedade deve começar com uma letra maiúscula")
    @Size(max = 30, message = "O comprimento do nome da propriedade não pode exceder 30 caracteres")
    private String name;
    private List<@Valid RoomRequestDTO> rooms;
    private @Valid NeighborhoodRequestDTO neighborhood;

}
