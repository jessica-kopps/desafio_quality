package desafio_quality.desafio_quality.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDTO {
    private String name;
    private Double width;
    private Double lenght;
}
