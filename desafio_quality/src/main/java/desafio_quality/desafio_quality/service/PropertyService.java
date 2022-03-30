package desafio_quality.desafio_quality.service;


import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    public Property createProperty(Property property) {
        property.setId(this.generateID());
        return this.repository.insert(property);
    }

    public Property getProperty(Long id) {
        return this.repository.findById(id);
    }

    private  Long generateID() {
        long lowerLimit = 123456712L;
        long upperLimit = 234567892L;
        Random r = new Random();
        return lowerLimit + ((long) (r.nextDouble() * (upperLimit - lowerLimit)));
    }
}
