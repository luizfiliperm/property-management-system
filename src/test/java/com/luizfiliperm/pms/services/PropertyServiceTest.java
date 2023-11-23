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

    @Test
    public void testUpdateById(){
        PropertyDtoResponse savedPropertyDtoResponse = propertyService.save(PropertyCreator.getPropertyReceive());

        PropertyDtoReceive propertyDtoReceive = PropertyCreator.getPropertyReceive();
        propertyDtoReceive.setName("Updated name");
        propertyDtoReceive.setContact("Updated contact");
        propertyDtoReceive.setNumberOfUnits(1);
        propertyDtoReceive.setDescription("Updated description");
        propertyDtoReceive.getAddress().setCep("Updated cep");
        propertyDtoReceive.getAddress().setNumber("Updated number");
        propertyDtoReceive.getAddress().setStreet("Updated street");
        propertyDtoReceive.getAddress().setCity("Updated city");
        propertyDtoReceive.getAddress().setState("Updated state");

        PropertyDtoResponse updatedPropertyDtoResponse = propertyService.updateById(savedPropertyDtoResponse.getId(), propertyDtoReceive);

        assertEquals(updatedPropertyDtoResponse.getName(), propertyDtoReceive.getName());
        assertEquals(updatedPropertyDtoResponse.getContact(), propertyDtoReceive.getContact());
        assertEquals(updatedPropertyDtoResponse.getNumberOfUnits(), propertyDtoReceive.getNumberOfUnits());
        assertEquals(updatedPropertyDtoResponse.getDescription(), propertyDtoReceive.getDescription());
        assertEquals(updatedPropertyDtoResponse.getAddress().getCep(), propertyDtoReceive.getAddress().getCep());
        assertEquals(updatedPropertyDtoResponse.getAddress().getNumber(), propertyDtoReceive.getAddress().getNumber());
        assertEquals(updatedPropertyDtoResponse.getAddress().getStreet(), propertyDtoReceive.getAddress().getStreet());
        assertEquals(updatedPropertyDtoResponse.getAddress().getCity(), propertyDtoReceive.getAddress().getCity());
        assertEquals(updatedPropertyDtoResponse.getAddress().getState(), propertyDtoReceive.getAddress().getState());
    }
}
