package desafio_quality.desafio_quality.service;
import desafio_quality.desafio_quality.model.Neighborhood;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PropertyTest {

    @Mock
    private PropertyRepository repository;

    @InjectMocks
    private PropertyService service;

    @BeforeEach
    private void setup(){
        Neighborhood neighborhood = new Neighborhood("Bairro 1", BigDecimal.valueOf(100));
        List<Room> roomList = Arrays.asList(
                new Room("Cozinha", 10.0, 10.0),
                new Room("Quarto", 20.0, 20.0));
        Property property = new Property(1L, "Propriedade 1", roomList, neighborhood);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(property);
    }

    @Test
    void calculateValueDistrictM2() {
        Property propertyCreated = service.getProperty(Mockito.any());
        assertEquals(service.getTotalArea(propertyCreated), 500.0);
    }

}
