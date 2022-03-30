package desafio_quality.desafio_quality.dto.request;

import desafio_quality.desafio_quality.dto.response.RoomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomListDTO {
    private List<RoomResponseDTO> roomList;
}
