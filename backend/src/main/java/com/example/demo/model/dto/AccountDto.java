package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountDto {
    private int idAccount;

    private String email;

    private String userName;

    private String phone;

    private String role;

    private Date createdTime;

    private Boolean isActivate;
}
