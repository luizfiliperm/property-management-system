package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Info.PersonalInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalInformationDto {
    private String phoneNumber;
    private LocalDate birthDate;
    private String nationality;
    private AddressDto address;
    private IdentificationDto identification;

    public PersonalInformationDto(PersonalInformation personalInformation){
        this.phoneNumber = personalInformation.getPhoneNumber();
        this.birthDate = personalInformation.getBirthDate();
        this.nationality = personalInformation.getNationality();
        this.address = new AddressDto(personalInformation.getAddress());
        this.identification = new IdentificationDto(personalInformation.getIdentification());
    }

    public PersonalInformation convertToPersonalInformation(){
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setPhoneNumber(this.phoneNumber);
        personalInformation.setBirthDate(this.birthDate);
        personalInformation.setAddress(this.address.convertToAddress());
        personalInformation.setIdentification(this.identification.convertToIdentification());
        return  personalInformation;
    }
}
