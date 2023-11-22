package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
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
        PropertyDtoReceive propertyDtoReceive = PropertyCreator.getPropertyReceive();

        PropertyDtoResponse savedProperty = propertyService.save(propertyDtoReceive);

        assertNotNull(savedProperty);
        assertEquals(propertyDtoReceive.getName(), savedProperty.getName());
        assertEquals(propertyDtoReceive.getContact(), savedProperty.getContact());
        assertEquals(propertyDtoReceive.getNumberOfUnits(), savedProperty.getNumberOfUnits());
        assertEquals(propertyDtoReceive.getDescription(), savedProperty.getDescription());
        assertEquals(propertyDtoReceive.getAddress().getCep(), savedProperty.getAddress().getCep());
        assertEquals(propertyDtoReceive.getAddress().getNumber(), savedProperty.getAddress().getNumber());
        assertEquals(propertyDtoReceive.getAddress().getStreet(), savedProperty.getAddress().getStreet());
        assertEquals(propertyDtoReceive.getAddress().getCity(), savedProperty.getAddress().getCity());
        assertEquals(propertyDtoReceive.getAddress().getState(), savedProperty.getAddress().getState());
    }

    @Test
    public void testFindById(){
        PropertyDtoResponse savedPropertyDtoResponse = propertyService.save(PropertyCreator.getPropertyReceive());

        assertEquals(propertyService.findById(savedPropertyDtoResponse.getId()), savedPropertyDtoResponse);
    }

    @Test
    public void testFindAll(){
        PropertyDtoResponse savedPropertyDtoResponse = propertyService.save(PropertyCreator.getPropertyReceive());

        assertEquals(propertyService.findAll(0, 1, "name", "asc").getContent().get(0), savedPropertyDtoResponse);

    }

    @Test
    public void testDelete(){
        PropertyDtoResponse savedPropertyDtoResponse = propertyService.save(PropertyCreator.getPropertyReceive());

        propertyService.delete(savedPropertyDtoResponse.getId());

        assertThrows(PmsException.class, () -> propertyService.findById(savedPropertyDtoResponse.getId()));

        try{
            propertyService.findById(savedPropertyDtoResponse.getId());
        }catch (PmsException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }


}
