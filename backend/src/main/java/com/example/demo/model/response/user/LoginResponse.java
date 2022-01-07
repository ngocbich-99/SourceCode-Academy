package com.example.demo.model.response.user;

import com.example.demo.model.dto.CourseEnrollDTO;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {

    private Long id;

    private String refreshToken;

    private String accessToken;

    private String tokenType = "Bearer";

    private String email;

    private String fullName;

    private String username;

    private String phone;

    private String role;

    private List<CourseEnrollDTO> courseEnrolls;

}