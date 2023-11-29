package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.user.UserAuthenticationDto;
import com.luizfiliperm.pms.dtos.user.UserLoginDto;
import com.luizfiliperm.pms.dtos.user.UserRegisterDto;
import com.luizfiliperm.pms.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pms/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserAuthenticationDto> register(@Valid @RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(authenticationService.register(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationDto> login(@RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok(authenticationService.login(userLoginDto));
    }
}
