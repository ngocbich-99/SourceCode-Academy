package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;

    private String email;

    private String username;

    private String fullName;

    private String phone;

    private String role;

    private Long createdTime;

    private Boolean isActivate;

    private List<CourseEnrollDTO> courseEnrolls;

}
