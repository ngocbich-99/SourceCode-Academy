package com.example.demo.constant;

import java.util.Arrays;
import java.util.List;

public class CommonConstant {

    private CommonConstant() {

    }

    public static final String SUCCESS = "Successful";

    public static final String AUTH_ERROR_TYPE = "AuthErrorType";

    public static final String AUTH_ERROR_TOKEN_EXPIRE = "TokenExpire";

    public static final String AUTH_TOKEN_EXPIRE_MESSAGE = "Phiên đăng nhập đã hết hạn hoặc tài khoản đang đăng nhập trên 1 thiết bị khác";

    public static final String NOT_LOGIN_MESSAGE = "Bạn chưa đăng nhập";

    public static final List<String> PATHS_NO_AUTHENTICATION = Arrays.asList("/**", "/swagger/**",
            "/swagger-ui.html#/**", "/v2/api-docs/**", "/health",
            "/api/auth/register",
            "/api/auth/login",
            "/swagger-ui/**",
            "/swagger-ui**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**"
    );
}
