package com.luizfiliperm.pms.infra.security;

public class RolePathMapping {
    public static final String[] REQUEST_WHITELIST = {
            "/pms/auth/login"
    };

    public static final String[] GLOBAL_MANAGER_REQUEST_WHITELIST = {
            "/pms/admin/**",
            "/pms/properties",
            "/pms/auth/register"
    };

    public static final String[] AUTHENTICATED_GET_REQUEST_WHITELIST = {
            "/pms/properties"
    };

    public static final String[] PROPERTY_MANAGER_REQUEST_WHITELIST = {
            "/pms/properties",
            "/pms/auth/register",
            "/pms/rooms"
    };

}
