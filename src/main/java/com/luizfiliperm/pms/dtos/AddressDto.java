package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    private String cep;

    private String number;

    private String street;

    private String city;

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
