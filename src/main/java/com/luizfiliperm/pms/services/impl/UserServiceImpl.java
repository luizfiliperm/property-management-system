package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.repositories.UserRepository;
import com.luizfiliperm.pms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new PmsException("User not found", HttpStatus.NOT_FOUND));
    }
}
