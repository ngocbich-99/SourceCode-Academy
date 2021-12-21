package com.example.demo.jwt;

import lombok.Data;

@Data
public class JwtSubject {

    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private String fullName;

    private String role;


}

