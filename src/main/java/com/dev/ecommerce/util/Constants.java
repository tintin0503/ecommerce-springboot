package com.dev.ecommerce.util;

public class Constants {

    public static final String API = "/api";

    public static final String SECURITY_NAME = "E-Commerce Application";

    public static final String[] PUBLIC_URLS = { "/v3/api-docs/**", "/swagger-ui/**", "/api/register/**", "/api/login" };
    public static final String[] USER_URLS = { "/api/public/**" };
    public static final String[] ADMIN_URLS = { "/api/admin/**" };

    public class Roles {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
