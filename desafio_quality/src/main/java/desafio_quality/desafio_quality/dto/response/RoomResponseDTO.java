package desafio_quality.desafio_quality.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {
    private String name;
    private Double width;
    private Double length;
    private Double area;
}
