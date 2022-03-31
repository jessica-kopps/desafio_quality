package desafio_quality.desafio_quality.factory;

import desafio_quality.desafio_quality.dto.request.NeighborhoodRequestDTO;
import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.request.RoomRequestDTO;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PropertyFactory {

    public static Property createProperty() {
        Neighborhood neighborhood = new Neighborhood("Pituba", BigDecimal.valueOf(100));
        List<Room> roomList = Arrays.asList(
                new Room("Cozinha", 10.0, 10.0),
                new Room("Quarto", 20.0, 20.0));
       return new Property(1L, "Propriedade 1", roomList, neighborhood);
    }

    public static Property createProperty(List<Room> roomList) {
        Neighborhood neighborhood = new Neighborhood("Bairro 1", BigDecimal.valueOf(100));
        return new Property(1L, "Propriedade 1", roomList, neighborhood);
    }

    public static List<Property> createProperties() {
        Neighborhood neighborhood1 = new Neighborhood("Bairro 1", BigDecimal.valueOf(100));
        List<Room> roomList1 = Arrays.asList(
                new Room("Cozinha", 10.0, 10.0),
                new Room("Quarto", 20.0, 20.0)
        );

        Neighborhood neighborhood2 = new Neighborhood("Bairro 2", BigDecimal.valueOf(100));
        List<Room> roomList2 = Arrays.asList(
                new Room("Cozinha", 20.0, 20.0),
                new Room("Quarto", 25.0, 25.0)
        );

        Neighborhood neighborhood3 = new Neighborhood("Bairro 3", BigDecimal.valueOf(100));
        List<Room> roomList3 = Arrays.asList(
                new Room("Cozinha", 15.0, 15.0),
                new Room("Quarto", 30.0, 30.0)
        );


        return Arrays.asList(
                new Property(1L, "Propriedade 1", roomList1, neighborhood1),
                new Property(2L, "Propriedade 2", roomList1, neighborhood1),
                new Property(3L, "Propriedade 3", roomList1, neighborhood1)
        );

    }

    public static PropertyRequestDTO createPropertyRequestDTO() {
        return new PropertyRequestDTO(
                "Fazenda da Joaquina",
                Arrays.asList(
                        new RoomRequestDTO(
                                "Quarto",
                                15.0,
                                15.0
                        ),
                        new RoomRequestDTO(
                                "Cozinha",
                                15.0,
                                15.0
                        )
                ),
                new NeighborhoodRequestDTO(
                        "Pituba",
                        BigDecimal.valueOf(1000.0)
                )
        );
    }

}
