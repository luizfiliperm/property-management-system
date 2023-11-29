package com.luizfiliperm.pms.dtos.user;

import com.luizfiliperm.pms.dtos.PersonalInformationDto;
import com.luizfiliperm.pms.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
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
