package com.luizfiliperm.pms.dtos.user;

import com.luizfiliperm.pms.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuthenticationDto {
    private UserResponseDto user;
    private String token;

    public UserAuthenticationDto(User user, String token){
        this.user = new UserResponseDto(user);
        this.token = token;
    }
}
