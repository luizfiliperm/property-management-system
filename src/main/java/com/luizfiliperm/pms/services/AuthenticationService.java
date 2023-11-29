package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.user.UserAuthenticationDto;
import com.luizfiliperm.pms.dtos.user.UserLoginDto;
import com.luizfiliperm.pms.dtos.user.UserRegisterDto;

public interface AuthenticationService {

    public UserAuthenticationDto register(UserRegisterDto userRegisterDto);

    public UserAuthenticationDto login(UserLoginDto userLoginDto);
}
