package desafio_quality.desafio_quality.service;


import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.repository.PropertyRepository;
import desafio_quality.desafio_quality.util.CalculationArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    public Property createProperty(Property property) {
        Long id = Property.generateID();
        property.setId(id);
       return this.repository.insert(property);
    }

    public BigDecimal calculateValueDistrictM2(Property property) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        property.getRoms().forEach(room ->
                totalPrice.add(CalculationArea.calculateTotalValueDistrictM2(
                property.getNeighborhood().getValueDistrictM2(), room.getLength(), room.getWidth()))
        );
        return  totalPrice;
    }


}
