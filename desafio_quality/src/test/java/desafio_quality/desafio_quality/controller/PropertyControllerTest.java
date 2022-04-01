package desafio_quality.desafio_quality.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import desafio_quality.desafio_quality.dto.mapper.MapperDTO;
import desafio_quality.desafio_quality.dto.request.NeighborhoodRequestDTO;
import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.request.RoomRequestDTO;
import desafio_quality.desafio_quality.dto.response.ErrorDTO;
import desafio_quality.desafio_quality.dto.response.PropertyPriceResponseDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.dto.response.RoomResponseDTO;
import desafio_quality.desafio_quality.factory.PropertyFactory;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.model.Room;
import desafio_quality.desafio_quality.service.PropertyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class PropertyControllerTest {

    @Value("${pathstorefile}")
    private String FILENAME;

    @Autowired
    private MockMvc mock;

    @Autowired
    private PropertyService service;

    @AfterEach
    public void afterTests() throws IOException {
        Files.deleteIfExists(Paths.get(FILENAME));
    }

    @Test
    public void testGetPropertyById() throws Exception {
        Property property = service.createProperty(PropertyFactory.createProperty());
        PropertyResponseDTO propertyDTO = MapperDTO.propertyToPropertyResponseDTO(property);

        MvcResult mvcResult =
                this.mock.perform(MockMvcRequestBuilders.get("/properties/{id}", property.getId()))
                        .andDo(print()).andExpect(status().isOk()).andReturn();
        String jsonReturned = mvcResult.getResponse().getContentAsString();
        PropertyResponseDTO propertyResult = new ObjectMapper().readValue(jsonReturned, PropertyResponseDTO.class);

        assertEquals(propertyDTO.getId(), propertyResult.getId());
    }

    @Test
    public void testPropertyNotFoundException() throws Exception {
        Long imaginaryId = 1L;
        ErrorDTO errorExpected = new ErrorDTO("PropertyNotFoundException",
                "O imovel de id " + imaginaryId + " nao foi encontrado.");

        MvcResult mvcResult =
                this.mock.perform(MockMvcRequestBuilders.get("/properties/{id}", imaginaryId))
                        .andDo(print()).andExpect(status().isNotFound()).andReturn();

        String jsonReturned = mvcResult.getResponse().getContentAsString();


        ErrorDTO errorResult = new ObjectMapper().readValue(jsonReturned, ErrorDTO.class);

        assertEquals(errorResult.getName(), errorExpected.getName());
        assertEquals(errorResult.getDescription(), errorExpected.getDescription());
    }

    @Test
    public void testValidationPostException() throws Exception {
        PropertyRequestDTO propertyRequestDTO = new PropertyRequestDTO();
        propertyRequestDTO.setName("");
        propertyRequestDTO.setRooms(new ArrayList<RoomRequestDTO>(){});
        propertyRequestDTO.getRooms().add(
                new RoomRequestDTO("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1000.00, 1000.00)
        );
        propertyRequestDTO.getRooms().add(
                new RoomRequestDTO("", 0.0, 0.0)
        );
        propertyRequestDTO.setNeighborhood(
                new NeighborhoodRequestDTO("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", BigDecimal.valueOf(1000000.00))
        );
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadRequestJson = writer.writeValueAsString(propertyRequestDTO);

        MvcResult mvcResult =
                this.mock.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadRequestJson))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();

        String payloadReturn = mvcResult.getResponse().getContentAsString();
        List<ErrorDTO> listResult = new ObjectMapper().readValue(payloadReturn, new TypeReference<List<ErrorDTO>>() {
        });

        assertEquals(listResult.size(), 9);
        assertEquals(listResult.stream().filter(error -> {
                    return error.getName().equals("MethodArgumentNotValidException");
                }).collect(Collectors.toList()).size(), 9);




    }

    @Test
    public void testNeighborhoodDoesntExists() throws Exception {

        ErrorDTO errorExpected = new ErrorDTO("NeighborhoodDoesntExists",
                "Este bairro nao existe!");

        PropertyRequestDTO requestObject = PropertyFactory.createPropertyRequestDTO(new NeighborhoodRequestDTO("NomeDoBairro", BigDecimal.valueOf(666.00)));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String payloadRequestJson = writer.writeValueAsString(requestObject);

        MvcResult mvcResult = this.mock.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadRequestJson))
                .andDo(print()).andExpect(status().isNotFound()).andReturn();

        String jsonReturned = mvcResult.getResponse().getContentAsString();
        ErrorDTO errorResult = new ObjectMapper().readValue(jsonReturned, ErrorDTO.class);
        assertEquals(errorResult.getName(), errorExpected.getName());
        assertEquals(errorResult.getDescription(), errorExpected.getDescription());


    }

    @Test
    public void testCalculatePrice() throws Exception {
        Property property = service.createProperty(PropertyFactory.createProperty());
        BigDecimal totalPrice = service.propertyCalculationValue(property);
        MvcResult mvcResult = this.mock.perform(MockMvcRequestBuilders.get("/properties/{id}/price", property.getId()))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String jsonReturned = mvcResult.getResponse().getContentAsString();
        PropertyPriceResponseDTO priceResult = new ObjectMapper().readValue(jsonReturned, PropertyPriceResponseDTO.class);
        assertEquals(priceResult.getTotalPrice(), totalPrice);

    }

    @Test
    public void testRoomAreas() throws Exception {
        Property property = service.createProperty(PropertyFactory.createProperty());
        MvcResult mvcResult = this.mock.perform(MockMvcRequestBuilders.get("/properties/{id}/roomAreas", property.getId()))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String jsonReturned = mvcResult.getResponse().getContentAsString();
        List<RoomResponseDTO> roomResult = new ObjectMapper().readValue(jsonReturned, new TypeReference<List<RoomResponseDTO>>() {
        });

        assertEquals(property.getRooms().get(0).getArea(), roomResult.get(0).getArea());
    }

    @Test
    public void testBiggestRoom() throws Exception {
        Property property = service.createProperty(PropertyFactory.createProperty());
        Room biggestRoomExpected = service.getBiggestRoom(property.getId());

        MvcResult mvcResult = this.mock.perform(MockMvcRequestBuilders.get("/properties/{id}/biggestRoom", property.getId()))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String jsonReturned = mvcResult.getResponse().getContentAsString();
        RoomResponseDTO roomResponseDTO = new ObjectMapper().readValue(jsonReturned, RoomResponseDTO.class);

        assertEquals(roomResponseDTO.getName(), biggestRoomExpected.getName());
    }

    @Test
    public void testPostProperty() throws Exception {
        PropertyRequestDTO requestObject = PropertyFactory.createPropertyRequestDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String payloadRequestJson = writer.writeValueAsString(requestObject);

        MvcResult mvcResult = this.mock.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadRequestJson))
                .andDo(print()).andExpect(status().isCreated()).andReturn();

        String jsonReturned = mvcResult.getResponse().getContentAsString();
        PropertyResponseDTO propertyResultOfCreation = new ObjectMapper().readValue(jsonReturned, PropertyResponseDTO.class);

        assertEquals(propertyResultOfCreation.getName(), requestObject.getName());
    }


}
