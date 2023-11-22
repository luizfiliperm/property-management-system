package com.luizfiliperm.pms.dtos.property;

import com.luizfiliperm.pms.dtos.AddressDto;
import com.luizfiliperm.pms.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyDtoReceive {

    private String name;

    private String contact;

    private Integer numberOfUnits;

    private String description;

    private AddressDto address;

    public PropertyDtoReceive(Property property){
        this.name = property.getName();
        this.contact = property.getContact();
        this.numberOfUnits = property.getNumberOfUnits();
        this.description = property.getDescription();
        this.address = new AddressDto(property.getAddress());
    }

    public Property convertToProperty(){
        return new Property(null, this.name, this.contact, this.numberOfUnits, this.description, this.address.convertToAddress());
    }
}
