package desafio_quality.desafio_quality.service;


import desafio_quality.desafio_quality.exception.NeighborhoodDoesntExists;
import desafio_quality.desafio_quality.exception.PropertyNotFoundException;
import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.repository.NeighborhoodRepository;
import desafio_quality.desafio_quality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;


@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    @Autowired
    NeighborhoodRepository neighborhoodRepository;

    public Property createProperty(Property property) {
        Long id = generateID();
        property.setId(id);

        property.getNeighborhood()
                .setName(
                        verifyingNeighborhoodExists(property.getNeighborhood().getName()
                        ));

        return this.repository.insert(property);
    }

    public Double getTotalArea(Property property){
        return property.getRooms().stream().reduce(0.0, (acc, room) -> acc + room.getArea(), Double::sum);
    }
    public BigDecimal propertyCalculationValue(Property property) {
        return property.getNeighborhood().getValueDistrictM2().multiply(
                BigDecimal.valueOf(getTotalArea(property)));
    }

    public Property getProperty(Long id) {
        Property property = this.repository.findById(id);

        if(property == null){
            throw new PropertyNotFoundException("O imovel de id " + id + " nao foi encontrado.");
        }

        return  property;
    }

    private  Long generateID() {
        long lowerLimit = 123456712L;
        long upperLimit = 234567892L;
        Random r = new Random();
        return lowerLimit + ((long) (r.nextDouble() * (upperLimit - lowerLimit)));

    }

    public Room getBiggestRoom(Long id) {
        Property property = this.getProperty(id);
        List<Room> rooms = property.getRooms();
        rooms.sort((b, a) -> a.getArea().compareTo(b.getArea()));
        return rooms.get(0);
    }

    private String verifyingNeighborhoodExists(String neighborhoodName) {
        Neighborhood neighborhood = neighborhoodRepository.findAll().stream().filter(neighborhoodRegistered -> {
            return neighborhoodRegistered.getName().equalsIgnoreCase(neighborhoodName);
        }).findFirst().orElseThrow( () -> new NeighborhoodDoesntExists("Este bairro nao existe!"));
        return neighborhood.getName();
    }
}
