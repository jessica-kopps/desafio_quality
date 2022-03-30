package desafio_quality.desafio_quality.model.mapper;


import desafio_quality.desafio_quality.dto.request.NeighborhoodRequestDTO;
import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.request.RoomRequestDTO;
import desafio_quality.desafio_quality.dto.response.NeighborhoodResponseDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.dto.response.RoomResponseDTO;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {
    private static Neighborhood neighborhoodDTOtoEntity(NeighborhoodRequestDTO dto) {
        return new Neighborhood(dto.getName(), dto.getPriceByArea());
    }

    private static Room roomDTOtoEntity(RoomRequestDTO dto) {
        return new Room(dto.getName(), dto.getLength(), dto.getWidth());
    }

    public static Property propertyDTOtoEntity(PropertyRequestDTO dto) {
        List<Room> list = dto.getRooms().stream().map(room -> {
            return roomDTOtoEntity(room);
        }).collect(Collectors.toList());

        return new Property(Property.generateID(),
                dto.getName(),
                list,
                neighborhoodDTOtoEntity(dto.getNeighborhood())
        );
    }

    public static PropertyResponseDTO entityToPropertyDTO(Property property) {

        List<RoomResponseDTO> listRooms = property.getRoms().stream().map(room -> entityToRoomDTO(room)).collect(Collectors.toList());

        return new PropertyResponseDTO(property.getName(), listRooms, entityToNeighborhoodDTO(property.getNeighborhood()));


    }

    public static RoomResponseDTO entityToRoomDTO(Room room) {
        return new RoomResponseDTO(room.getName(), room.getWidth(), room.getLength());

    }

    public static NeighborhoodResponseDTO entityToNeighborhoodDTO(Neighborhood neighborhood) {
        return new NeighborhoodResponseDTO(neighborhood.getName(), neighborhood.getPriceByArea());
    }


}
