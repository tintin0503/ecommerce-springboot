package com.dev.ecommerce.util;

public class Constants {

    public static final String API = "/api";
    public static final String ADMIN_PATH = "/admin";
    public static final String INTERNAL_PATH = "/internal";

    public static final String SECURITY_NAME = "E-Commerce Application";

    public static final String SORT_USERS_BY = "userId";
    public static final String SORT_CATEGORIES_BY = "categoryId";
    public static final String SORT_ORDERS_BY = "totalAmount";
    public static final String SORT_PRODUCTS_BY = "productId";
    public static final String SORT_DIR = "asc";

    public static final String[] PUBLIC_URLS = { "/v3/api-docs/**", "/swagger-ui/**", "/api/internal/register/**", "/api/internal/login" };
    public static final String[] USER_URLS = { "/api/internal/public/**" };
    public static final String[] ADMIN_URLS = { "/api/admin/**" };

    public static final Long ADMIN_ID = 101L;
    public static final Long USER_ID = 102L;


    public static class Roles {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }

    public static class Pages {
        public static final String PAGE_NUMBER = "0";
        public static final String PAGE_SIZE = "2";
    }
}
