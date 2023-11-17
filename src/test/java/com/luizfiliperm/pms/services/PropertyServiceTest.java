package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.AddressDto;
import com.luizfiliperm.pms.dtos.PropertyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testRegister(){
        PropertyDto propertyDto = getPropertyDto();

        PropertyDto savedProperty = propertyService.register(propertyDto);

        assertNotNull(savedProperty);
        assertEquals(propertyDto.getName(), savedProperty.getName());
        assertEquals(propertyDto.getContact(), savedProperty.getContact());
        assertEquals(propertyDto.getNumberOfUnits(), savedProperty.getNumberOfUnits());
        assertEquals(propertyDto.getDescription(), savedProperty.getDescription());
        assertEquals(propertyDto.getAddress().getCep(), savedProperty.getAddress().getCep());
        assertEquals(propertyDto.getAddress().getNumber(), savedProperty.getAddress().getNumber());
        assertEquals(propertyDto.getAddress().getStreet(), savedProperty.getAddress().getStreet());
        assertEquals(propertyDto.getAddress().getCity(), savedProperty.getAddress().getCity());
        assertEquals(propertyDto.getAddress().getState(), savedProperty.getAddress().getState());

    }

    private static PropertyDto getPropertyDto() {
        AddressDto addressDto = new AddressDto();

        addressDto.setCep("30203200");
        addressDto.setNumber("42");
        addressDto.setStreet("Nobody street");
        addressDto.setCity("City of tests");
        addressDto.setState("PB");

        PropertyDto propertyDto = new PropertyDto();

        propertyDto.setName("Test Hotel");
        propertyDto.setContact("+5588999999999");
        propertyDto.setNumberOfUnits(0);
        propertyDto.setDescription("No description");
        propertyDto.setAddress(addressDto);
        return propertyDto;
    }
}
