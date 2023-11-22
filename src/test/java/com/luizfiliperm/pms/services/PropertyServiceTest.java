package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PropertyDto;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.property.PropertyCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testRegister(){
        PropertyDto propertyDto = PropertyCreator.getPropertyDto();

        PropertyDto savedProperty = propertyService.save(propertyDto);

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
        PropertyDto savedPropertyDto = propertyService.save(PropertyCreator.getPropertyDto());

        assertEquals(propertyService.findById(savedPropertyDto.getId()), savedPropertyDto);
    }

    @Test
    public void testFindAll(){
        PropertyDto savedPropertyDto = propertyService.save(PropertyCreator.getPropertyDto());

        assertEquals(propertyService.findAll(0, 1, "name", "asc").getContent().get(0), savedPropertyDto);

    }

    @Test
    public void testDelete(){
        PropertyDto savedPropertyDto = propertyService.save(PropertyCreator.getPropertyDto());

        propertyService.delete(savedPropertyDto.getId());

        assertThrows(PmsException.class, () -> propertyService.findById(savedPropertyDto.getId()));

        try{
            propertyService.findById(savedPropertyDto.getId());
        }catch (PmsException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }


}
