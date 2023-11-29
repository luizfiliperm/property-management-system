package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.user.UserAuthenticationDto;
import com.luizfiliperm.pms.dtos.user.UserLoginDto;
import com.luizfiliperm.pms.dtos.user.UserRegisterDto;
import com.luizfiliperm.pms.entities.user.User;
import com.luizfiliperm.pms.entities.user.enums.UserRole;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.infra.security.services.JwtService;
import com.luizfiliperm.pms.repositories.UserRepository;
import com.luizfiliperm.pms.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserAuthenticationDto register(UserRegisterDto userRegisterDto) {
        User user = userRegisterDto.convertToUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_USER);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new UserAuthenticationDto(user, jwtToken);
    }

    @Override
    public UserAuthenticationDto login(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                )
        );

        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(() -> new PmsException("User not found", HttpStatus.NOT_FOUND));

        return new UserAuthenticationDto(user, jwtService.generateToken(user));
    }
}
