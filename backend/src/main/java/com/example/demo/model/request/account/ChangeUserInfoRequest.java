package com.example.demo.model.request.account;

import lombok.Data;

@Data
public class ChangeUserInfoRequest {

    private String fullName;

    private String phone;
}
