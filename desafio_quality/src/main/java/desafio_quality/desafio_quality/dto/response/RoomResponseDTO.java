package desafio_quality.desafio_quality.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDTO {
    private String name;
    private Double width;
    private Double length;
}
