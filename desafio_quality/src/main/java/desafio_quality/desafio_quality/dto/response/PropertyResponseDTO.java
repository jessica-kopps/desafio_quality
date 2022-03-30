package desafio_quality.desafio_quality.dto.response;

import desafio_quality.desafio_quality.dto.request.NeighborhoodRequestDTO;
import desafio_quality.desafio_quality.dto.request.RoomRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDTO {

    private String name;
    private List<RoomResponseDTO> rooms;
    private NeighborhoodResponseDTO neighborhood;

}
