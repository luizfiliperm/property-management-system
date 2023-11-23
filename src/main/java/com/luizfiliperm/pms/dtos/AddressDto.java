package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    @NotBlank(message = "The cep is required")
    private String cep;

    @NotBlank(message = "The number is required")
    private String number;

    @NotBlank(message = "The street is required")
    private String street;

    @NotBlank(message = "The city is required")
    private String city;

    @NotBlank(message = "The state is required")
    private String state;

    public AddressDto(Address address){
        this.cep = address.getCep();
        this.number = address.getNumber();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
    }

    public Address convertToAddress(){
        return new Address(null, this.cep, this.number, this.street, this.city, this.state, null);
    }
}
