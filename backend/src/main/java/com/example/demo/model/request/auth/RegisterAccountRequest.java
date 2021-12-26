package com.example.demo.model.request.auth;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString(exclude = {"newPassword", "reEnterNewPassword"})
@Data
public class RegisterAccountRequest {

    @NotEmpty(message = "Họ tên người dùng không được để trống")
    private String fullName;

    @NotEmpty(message = "Email không được để trống")
    private String email;

    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;

    @NotEmpty(message = "Nhập lại mật khẩu không được để trống")
    private String reEnterPassword;
}