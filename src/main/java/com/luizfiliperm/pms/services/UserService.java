package com.luizfiliperm.pms.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByEmail(String email);
}
