package com.luizfiliperm.pms.dtos.property;

import com.luizfiliperm.pms.dtos.AddressDto;
import com.luizfiliperm.pms.entities.Property;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyDtoReceive {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The contact is required")
    private String contact;

    private Integer numberOfUnits;

    @NotBlank(message = "The description is required")
    private String description;

    @Valid
    @NotNull(message = "The address is required")
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
