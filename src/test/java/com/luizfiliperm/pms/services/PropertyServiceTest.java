package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PropertyDto;
import com.luizfiliperm.pms.property.PropertyCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testRegister(){
        PropertyDto propertyDto = PropertyCreator.getPropertyDto();

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

    @Test
    public void testFindById(){
        PropertyDto savedPropertyDto = propertyService.register(PropertyCreator.getPropertyDto());

        assertEquals(propertyService.findById(savedPropertyDto.getId()), savedPropertyDto);

    }


}
