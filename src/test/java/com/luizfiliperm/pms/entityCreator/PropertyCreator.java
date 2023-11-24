package com.luizfiliperm.pms.entityCreator;

import com.luizfiliperm.pms.dtos.AddressDto;
import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;

import java.util.List;

public class PropertyCreator {
    public static PropertyDtoReceive getPropertyReceive() {
        AddressDto addressDto = new AddressDto();

        addressDto.setCep("30203200");
        addressDto.setNumber("42");
        addressDto.setStreet("Nobody street");
        addressDto.setCity("City of tests");
        addressDto.setState("PB");

        PropertyDtoReceive propertyDtoReceive = new PropertyDtoReceive();

        propertyDtoReceive.setName("Test Hotel");
        propertyDtoReceive.setContact("+5588999999999");
        propertyDtoReceive.setNumberOfUnits(0);
        propertyDtoReceive.setDescription("No description");
        propertyDtoReceive.setAddress(addressDto);
        return propertyDtoReceive;
    }

    public static PropertyDtoResponse getPropertyDtoResponse(){
        PropertyDtoResponse propertyDtoResponse = new PropertyDtoResponse(getPropertyReceive().convertToProperty()) ;

        propertyDtoResponse.setId(1L);
        return propertyDtoResponse;
    }

    public static PropertyDtoReceive getInvalidDtoReceive(){
        PropertyDtoReceive propertyDtoReceive = getPropertyReceive();
        propertyDtoReceive.setName(null);
        propertyDtoReceive.setDescription(null);
        propertyDtoReceive.getAddress().setStreet(null);

        return propertyDtoReceive;
    }

    public static PageResponse<PropertyDtoResponse> getPageResponse(){
        return new PageResponse<>(List.of(getPropertyDtoResponse()), 0, 1, 1L, 1, true);
    }

}
