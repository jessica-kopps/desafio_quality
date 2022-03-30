package desafio_quality.desafio_quality.dto.mapper;

import desafio_quality.desafio_quality.dto.response.NeighborhoodResponseDTO;
import desafio_quality.desafio_quality.dto.response.PropertyPriceResponseDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.dto.response.RoomResponseDTO;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;

import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class MapperDTO {

        public static PropertyPriceResponseDTO propertyToPropertyPriceDTO(Property property, BigDecimal totalPrice) {
            return new PropertyPriceResponseDTO(propertyToPropertyResponseDTO(property), totalPrice);
        }

        public static NeighborhoodResponseDTO neighborhoodToNeighborhoodResponseDTO(Neighborhood neighborhood) {
            return new NeighborhoodResponseDTO(
                    neighborhood.getName(),
                    neighborhood.getValueDistrictM2()
            );
        }

        public static RoomResponseDTO roomToRoomResponseDTO(Room room) {
            return new RoomResponseDTO(
                    room.getName(),
                    room.getWidth(),
                    room.getLength(),
                    room.getArea()
            );
        }

        public static PropertyResponseDTO propertyToPropertyResponseDTO(Property property) {
            List<RoomResponseDTO> list = property.getRooms().stream().map(room -> roomToRoomResponseDTO(room)).collect(Collectors.toList());
            return new PropertyResponseDTO(
                    property.getId(),
                    property.getName(),
                    list,
                    neighborhoodToNeighborhoodResponseDTO(property.getNeighborhood())
            );
        }

}
