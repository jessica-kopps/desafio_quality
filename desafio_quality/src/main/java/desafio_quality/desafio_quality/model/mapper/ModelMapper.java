package desafio_quality.desafio_quality.model.mapper;


import desafio_quality.desafio_quality.dto.request.NeighborhoodRequestDTO;
import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.request.RoomRequestDTO;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {

    private static Long idGlobal;

    private static Neighborhood neighborhoodDTO (NeighborhoodRequestDTO dto) {
        return new Neighborhood(dto.getName(), dto.getPriceByArea());
    }

    private static Room roomDTO (RoomRequestDTO dto) {
        return new Room(dto.getName(), dto.getLength(), dto.getWidth());
    }

    public static Property propertyDTO (PropertyRequestDTO dto) {
        List<Room> list = dto.getRooms().stream().map(room -> {
            return roomDTO(room);
        }).collect(Collectors.toList());
        idGlobal++;
        return new Property(idGlobal,
                dto.getName(),
                list,
                neighborhoodDTO(dto.getNeighborhood())
                );
    }
}
