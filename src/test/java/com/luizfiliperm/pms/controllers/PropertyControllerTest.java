package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.exceptions.ErrorMessage;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.json.JsonConverter;
import com.luizfiliperm.pms.property.PropertyCreator;
import com.luizfiliperm.pms.services.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    @Test
    public void testAddProperty() throws Exception {
        PropertyDtoResponse mockProperty = PropertyCreator.getPropertyDtoResponse();

        when(propertyService.save(any(PropertyDtoReceive.class))).thenReturn(mockProperty);

        mockMvc.perform(post("/pms/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.convertToJson(PropertyCreator.getPropertyReceive())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is("Test Hotel")))
                .andExpect(jsonPath("$.contact", is("+5588999999999")))
                .andExpect(jsonPath("$.numberOfUnits", is(0)))
                .andExpect(jsonPath("$.description", is("No description")))
                .andExpect(jsonPath("$.address.cep", is("30203200")))
                .andExpect(jsonPath("$.address.number", is("42")))
                .andExpect(jsonPath("$.address.street", is("Nobody street")))
                .andExpect(jsonPath("$.address.city", is("City of tests")))
                .andExpect(jsonPath("$.address.state", is("PB"))
        );

    }

    @Test
    public void testGetPropertyById() throws Exception {
        PropertyDtoResponse mockProperty = PropertyCreator.getPropertyDtoResponse();

        when(propertyService.findById(1L)).thenReturn(mockProperty);

        mockMvc.perform(get("/pms/properties/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Hotel")))
                .andExpect(jsonPath("$.contact", is("+5588999999999")))
                .andExpect(jsonPath("$.numberOfUnits", is(0)))
                .andExpect(jsonPath("$.description", is("No description")))
                .andExpect(jsonPath("$.address.cep", is("30203200")))
                .andExpect(jsonPath("$.address.number", is("42")))
                .andExpect(jsonPath("$.address.street", is("Nobody street")))
                .andExpect(jsonPath("$.address.city", is("City of tests")))
                .andExpect(jsonPath("$.address.state", is("PB"))
                );
    }

    @Test
    public void testNotFindProperty() throws Exception {
        ErrorMessage mockError = new ErrorMessage("Property not found with id: 1", 404, System.currentTimeMillis(), "/pms/properties/1");
        when(propertyService.findById(1L)).thenThrow(new PmsException("Property not found with id: 1", HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/pms/properties/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(mockError.getMessage())))
                .andExpect(jsonPath("$.status", is(mockError.getStatus())))
                .andExpect(jsonPath("$.path", is(mockError.getPath()))
                );
    }

}
