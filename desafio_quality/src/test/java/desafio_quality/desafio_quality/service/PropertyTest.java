package desafio_quality.desafio_quality.service;
import desafio_quality.desafio_quality.factory.PropertyFactory;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.repository.NeighborhoodRepository;
import desafio_quality.desafio_quality.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class PropertyTest {

    @Mock
    private PropertyRepository repository;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    private PropertyService service;

    @BeforeEach
    private void setup(){
        Property property = PropertyFactory.createProperty();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(property);
        Mockito.when(neighborhoodRepository.findAll()).thenReturn(Arrays.asList(new Neighborhood(property.getNeighborhood().getName(),
                property.getNeighborhood().getValueDistrictM2())));
        Mockito.when(repository.insert(Mockito.any())).thenReturn(property);
    }

    @Test
    void getTotalAreaShouldReturnTheCorrectAreaValue() {
        Property propertyCreated = service.getProperty(Mockito.any());
        assertEquals(service.getTotalArea(propertyCreated), 500.0);
    }

    @Test
    void getBiggestRoomShouldReturnTheBiggestRoom() {
        Room result = service.getBiggestRoom(Mockito.any());
        assertEquals(result.getName(), "Quarto");
        assertEquals(result.getWidth(), 20.0);
        assertEquals(result.getLength(), 20.0);
    }

    @Test
    void createProperty () {
        Property property = PropertyFactory.createProperty();
        Property createProperty = service.createProperty(property);
        assertEquals(createProperty.getNeighborhood().getName(), "Bairro 1");
    }


}
