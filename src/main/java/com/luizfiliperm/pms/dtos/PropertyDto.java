package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyDto {
    private Long id;

    private String name;

    private String contact;

    private Integer numberOfUnits;

    private String description;

    private AddressDto address;

    public PropertyDto(Property property){
        this.id = property.getId();
        this.name = property.getName();
        this.numberOfUnits = property.getNumberOfUnits();
        this.description = property.getDescription();
        this.address = new AddressDto(property.getAddress());
    }

    public Property convertToProperty(){
        return new Property(this.id, this.name, this.contact, this.numberOfUnits, this.description, this.address.convertToAddress());
    }
}
