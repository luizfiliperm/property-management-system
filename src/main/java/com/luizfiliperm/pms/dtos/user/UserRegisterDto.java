package com.luizfiliperm.pms.dtos.user;

import com.luizfiliperm.pms.dtos.PersonalInformationDto;
import com.luizfiliperm.pms.entities.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {

    @NotBlank(message = "The first name is required")
    private String firstName;

    @NotBlank(message = "The last name is required")
    private String lastName;

    @NotBlank(message = "The email is required")
    private String email;

    @NotBlank(message = "The password is required")
    private String password;

    @Valid
    @NotNull(message = "The personal information is required")
    private PersonalInformationDto personalInformation;

    public UserRegisterDto(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.personalInformation = new PersonalInformationDto(user.getPersonalInformation());
    }

    public User convertToUser(){
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setPersonalInformation(this.personalInformation.convertToPersonalInformation());
        return user;
    }
}
