package com.luizfiliperm.pms.util;

import com.luizfiliperm.pms.entities.property.Property;
import com.luizfiliperm.pms.entities.user.User;
import org.springframework.security.core.Authentication;

public class AuthUtil {

    public static User retrieveUserFromAuthentication(Authentication authentication){
        return (User) authentication.getPrincipal();
    }

    public static Property retrievePropertyFromAuthentication(Authentication authentication){
        return retrieveUserFromAuthentication(authentication).getProperty();
    }
}
