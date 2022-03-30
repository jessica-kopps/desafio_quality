package desafio_quality.desafio_quality.controller;

import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.mapper.ModelMapper;
import desafio_quality.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    // /properties/12312/price
    @GetMapping(value = "{id}/price")
    public ResponseEntity<PropertyResponseDTO> calculatePrice(@PathVariable Long id) {
        Property property = this.service.findById(id);
        BigDecimal totalPrice = this.service.calculateValueDistrictM2(property);

        Property property = ModelMapper.entityToPropertyDTO
    }
}
