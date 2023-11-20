package com.luizfiliperm.pms.property;

import com.luizfiliperm.pms.dtos.AddressDto;
import com.luizfiliperm.pms.dtos.PropertyDto;

public class PropertyCreator {
    public static PropertyDto getPropertyDto() {
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

    public static PropertyDto getPropertyDtoResponse(){
        PropertyDto propertyDto = getPropertyDto();

        propertyDto.setId(1L);
        return propertyDto;
    }


}
