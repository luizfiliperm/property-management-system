package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Info.PersonalInformation;
import com.luizfiliperm.pms.util.DateTimeUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalInformationDto {

    @NotBlank(message = "The phone number is required")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Invalid birth date, the correct format is dd/MM/yyyy")
    private String birthDate;

    @NotBlank(message = "The nationality is required")
    private String nationality;

    @Valid
    @NotNull(message = "The address is required")
    private AddressDto address;

    @Valid
    @NotNull(message = "The identification is required")
    private IdentificationDto identification;

    public PersonalInformationDto(PersonalInformation personalInformation){
        this.phoneNumber = personalInformation.getPhoneNumber();
        this.birthDate = DateTimeUtil.convertLocalDateToString(personalInformation.getBirthDate());
        this.nationality = personalInformation.getNationality();
        this.address = new AddressDto(personalInformation.getAddress());
        this.identification = new IdentificationDto(personalInformation.getIdentification());
    }

    public PersonalInformation convertToPersonalInformation(){
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setPhoneNumber(this.phoneNumber);
        personalInformation.setBirthDate(DateTimeUtil.convertStringToLocalDate(this.birthDate));
        personalInformation.setNationality(this.nationality);
        personalInformation.setAddress(this.address.convertToAddress());
        personalInformation.setIdentification(this.identification.convertToIdentification());
        return  personalInformation;
    }
}
