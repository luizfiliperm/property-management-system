package com.luizfiliperm.pms.entities.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ROLE_GLOBAL_MANAGER("global_manager"),
    ROLE_PROPERTY_MANAGER("property_manager"),
    ROLE_PROPERTY_RECEPTIONIST("property_receptionist"),
    ROLE_USER("user");

    private String role;
}
