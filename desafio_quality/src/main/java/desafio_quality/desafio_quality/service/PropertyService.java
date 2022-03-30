package desafio_quality.desafio_quality.service;


import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
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

    public Property createProperty(Property property) {
        Long id = generateID();
        property.setId(id);
       return this.repository.insert(property);
    }

    public BigDecimal calculateValueDistrictM2(Property property) {
        Double totalArea = property.getRooms().stream().reduce(0.0, (acc, room) -> acc + room.getArea(), Double::sum);
        return property.getNeighborhood().getValueDistrictM2().multiply(BigDecimal.valueOf(totalArea));
    }

    public Property getProperty(Long id) {
        Property property = this.repository.findById(id);

        if(property == null){
            throw new IllegalArgumentException("Product doesnt exist.");
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
}
