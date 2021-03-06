package desafio_quality.desafio_quality.controller;

import desafio_quality.desafio_quality.dto.mapper.MapperDTO;
import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.response.PropertyPriceResponseDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.dto.response.RoomResponseDTO;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.model.mapper.ModelMapper;
import desafio_quality.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    PropertyService service;

    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO, UriComponentsBuilder uriBuilder) {
        Property property = ModelMapper.propertyDTOtoEntity(propertyRequestDTO);
        PropertyResponseDTO propertyResponseDTO = ModelMapper.entityToPropertyDTO(this.service.createProperty(property));

        URI uri = uriBuilder
                .path("{id}")
                .buildAndExpand(property.getId())
                .toUri();

        return ResponseEntity.created(uri).body(propertyResponseDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ModelMapper.entityToPropertyDTO(this.service.getProperty(id)));
    }

    @GetMapping("{id}/roomAreas")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsAreas(@PathVariable Long id){
        Property property = this.service.getProperty(id);
        return ResponseEntity.ok(ModelMapper.entityToRoomDTO(property.getRooms()));
    }

    @GetMapping(value = "{id}/price")
    public ResponseEntity<PropertyPriceResponseDTO> calculatePrice(@PathVariable Long id) {
        Property property = this.service.getProperty(id);
        BigDecimal totalPrice = this.service.propertyCalculationValue(property);
        PropertyPriceResponseDTO propertyPrice = MapperDTO.propertyToPropertyPriceDTO(property, totalPrice);
        return ResponseEntity.ok().body(propertyPrice);
    }

    @GetMapping("{id}/biggestRoom")
    public ResponseEntity<RoomResponseDTO> getBiggestRoom(@PathVariable Long id){
        Room room = this.service.getBiggestRoom(id);
        return ResponseEntity.ok(ModelMapper.entityToRoomDTO(room));
    }

}
