package desafio_quality.desafio_quality.repository;

import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.util.JsonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PropertyRepository implements IPropertyRepository<Long, Property> {

    private static final String FILENAME = "properties.json";



    @Override
    public Property findById(Long id) {
        JsonFileUtil<Property> jsonFile = new JsonFileUtil<Property>(FILENAME);
        return this.findAll().stream()
                .filter(property -> {
                    return property.getId().equals(id);
                }).findFirst()
                .orElse(null);
    }

    @Override
    public List<Property> findAll() {
        JsonFileUtil<Property> jsonFile = new JsonFileUtil<Property>(FILENAME);
        return jsonFile.read(Property.class);
    }

    @Override
    public Property insert(Property entity) {
        JsonFileUtil<Property> jsonFile = new JsonFileUtil<Property>(FILENAME);
        return jsonFile.append(entity, Property.class);
    }

    @Override
    public Property update(Long id, Property entity) {
        JsonFileUtil<Property> jsonFile = new JsonFileUtil<Property>(FILENAME);
        List<Property> listUpdated =  this.findAll().stream()
                .map(property -> {
                    if(property.getId().equals(id)) return entity;
                    return property;
                }).collect(Collectors.toList());
        jsonFile.update(listUpdated);
        return findById(id);
    }
}
