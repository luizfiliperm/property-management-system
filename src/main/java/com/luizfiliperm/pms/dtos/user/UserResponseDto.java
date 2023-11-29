package com.luizfiliperm.pms.dtos.user;

import com.luizfiliperm.pms.dtos.PersonalInformationDto;
import com.luizfiliperm.pms.entities.user.User;
import com.luizfiliperm.pms.entities.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private PersonalInformationDto personalInformation;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.personalInformation = new PersonalInformationDto(user.getPersonalInformation());
    }

    public User convertToUser(){
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setRole(this.role);
        user.setPersonalInformation(this.personalInformation.convertToPersonalInformation());
        return user;
    }

}
