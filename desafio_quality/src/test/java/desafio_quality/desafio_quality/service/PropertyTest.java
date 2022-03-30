package desafio_quality.desafio_quality.service;

import desafio_quality.desafio_quality.repository.PropertyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class PropertyTest {

    @Mock
    private PropertyRepository repository;

    @InjectMocks
    private PropertyService service;

    void calculateValueDistrictM2() {

    }

}
