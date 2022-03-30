package desafio_quality.desafio_quality.controller;

import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.mapper.ModelMapper;
import desafio_quality.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    PropertyService service;


    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(@RequestBody PropertyRequestDTO propertyRequestDTO) {
        Property property = ModelMapper.propertyDTOtoEntity(propertyRequestDTO);
        return ResponseEntity.ok(ModelMapper.entityToPropertyDTO(this.service.createProperty(property)));


    }

}
