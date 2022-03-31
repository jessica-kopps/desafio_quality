package desafio_quality.desafio_quality.service;
import desafio_quality.desafio_quality.factory.PropertyFactory;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class PropertyTest {

    @Mock
    private PropertyRepository repository;

    @InjectMocks
    private PropertyService service;

    @BeforeEach
    private void setup(){
        Property property = PropertyFactory.createProperty();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(property);
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


}
