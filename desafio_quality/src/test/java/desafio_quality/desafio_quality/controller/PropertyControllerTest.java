package desafio_quality.desafio_quality.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import desafio_quality.desafio_quality.dto.mapper.MapperDTO;
import desafio_quality.desafio_quality.dto.request.PropertyRequestDTO;
import desafio_quality.desafio_quality.dto.response.PropertyPriceResponseDTO;
import desafio_quality.desafio_quality.dto.response.PropertyResponseDTO;
import desafio_quality.desafio_quality.factory.PropertyFactory;
import desafio_quality.desafio_quality.model.Property;
import desafio_quality.desafio_quality.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PropertyControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private PropertyService service;

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
    public void testCalculatePrice() throws Exception {
        Property property = service.createProperty(PropertyFactory.createProperty());
        BigDecimal totalPrice = service.propertyCalculationValue(property);
        MvcResult mvcResult = this.mock.perform(MockMvcRequestBuilders.get("/properties/{id}/price",property.getId()))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String jsonReturned = mvcResult.getResponse().getContentAsString();
        PropertyPriceResponseDTO priceResult = new ObjectMapper().readValue(jsonReturned, PropertyPriceResponseDTO.class);
        assertEquals(priceResult.getTotalPrice(), totalPrice);

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
