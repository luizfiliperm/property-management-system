package com.luizfiliperm.pms.dtos.property;

import com.luizfiliperm.pms.dtos.AddressDto;
import com.luizfiliperm.pms.entities.property.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyDtoResponse {
    private Long id;

    private String name;

    private String contact;

    private String description;

    private AddressDto address;

    public PropertyDtoResponse(Property property){
        this.id = property.getId();
        this.name = property.getName();
        this.contact = property.getContact();
        this.description = property.getDescription();
        this.address = new AddressDto(property.getAddress());
    }

    public Property convertToProperty(){
        Property property = new Property();
        property.setId(this.id);
        property.setName(this.name);
        property.setContact(this.contact);
        property.setDescription(this.description);
        property.setAddress(this.address.convertToAddress());
        return property;
    }
}
