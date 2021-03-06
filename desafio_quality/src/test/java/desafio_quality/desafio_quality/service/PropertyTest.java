package desafio_quality.desafio_quality.service;

import desafio_quality.desafio_quality.exception.NeighborhoodDoesntExists;
import desafio_quality.desafio_quality.exception.PropertyNotFoundException;
import desafio_quality.desafio_quality.factory.PropertyFactory;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.repository.NeighborhoodRepository;
import desafio_quality.desafio_quality.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class PropertyTest {

    @Mock
    private PropertyRepository repository;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    private PropertyService service;

    private void setupGetProperty() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(null);
    }

    private void setupFindBy() {
        Property property = PropertyFactory.createProperty();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(property);
    }

    private void setupFindAll() {
        Property property = PropertyFactory.createProperty();
        Mockito.when(neighborhoodRepository.findAll()).thenReturn(Arrays.asList(new Neighborhood(property.getNeighborhood().getName(),
                property.getNeighborhood().getValueDistrictM2())));
        Mockito.when(repository.insert(Mockito.any())).thenReturn(property);
    }

    private void setupFindAllThrowsException() {
        Property property = PropertyFactory.createProperty();
        Mockito.when(neighborhoodRepository.findAll()).thenReturn(Arrays.asList(new Neighborhood(property.getNeighborhood().getName(),
                property.getNeighborhood().getValueDistrictM2())));
    }

    @Test
    void shouldReturnTheCorrectAreaValue() {
        this.setupFindBy();
        Property propertyCreated = service.getProperty(Mockito.any());
        assertEquals(service.getTotalArea(propertyCreated), 500.0);
    }

    @Test
    void shouldReturnTheBiggestRoom() {
        this.setupFindBy();
        Room result = service.getBiggestRoom(Mockito.any());
        assertEquals(result.getName(), "Quarto");
        assertEquals(result.getWidth(), 20.0);
        assertEquals(result.getLength(), 20.0);
    }

    @Test
    void shouldCreatePropertyWhenNeighborhoodExists() {
        this.setupFindAll();
        Property property = PropertyFactory.createProperty();
        Property createProperty = service.createProperty(property);
        assertEquals(createProperty.getNeighborhood().getName(), "Pituba");
    }

    @Test
    void shouldThrowsExceptionWhenNeighborhoodDoesntExists() {
        this.setupFindAllThrowsException();
        Property property = PropertyFactory.createProperty();
        property.setNeighborhood(new Neighborhood("NomeDoBairro", BigDecimal.valueOf(666.00)));
        assertThrows(NeighborhoodDoesntExists.class, () -> {
            service.createProperty(property);
        });
    }

    @Test
    void shouldCalculateCorrectRoomArea() {
        this.setupFindAll();
        Property property = PropertyFactory.createProperty();
        Property createProperty = service.createProperty(property);
        assertEquals(createProperty.getRooms().get(0).getArea(), property.getRooms().get(0).calculateArea());
    }

    @Test
    void shouldTrowsExceptionWhenPropertyIsntFound() {
        this.setupGetProperty();
        assertThrows(PropertyNotFoundException.class, () -> {
            service.getProperty(null);
        });
    }

    @Test
    void shouldCalculatePropertyValue() {
        Property property = PropertyFactory.createProperty();
        assertEquals(service.propertyCalculationValue(property),
                property.getNeighborhood().getValueDistrictM2().multiply(
                        BigDecimal.valueOf(property.getRooms().stream().reduce(0.0, (acc, room) -> acc + room.getArea(), Double::sum))));
    }
}
