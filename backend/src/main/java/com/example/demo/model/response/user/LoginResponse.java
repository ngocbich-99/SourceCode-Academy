package com.example.demo.model.response.user;

import lombok.Data;

@Data
public class LoginResponse {

    private String refreshToken;

    private String accessToken;

    private String tokenType = "Bearer";

    private String email;

    private String username;

    private String phone;

    private String role;
}