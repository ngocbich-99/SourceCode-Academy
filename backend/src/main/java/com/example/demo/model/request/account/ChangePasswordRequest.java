package com.example.demo.model.request.account;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(exclude = {"oldPassword","newPassword"})
public class ChangePasswordRequest {

    @NotEmpty(message = "Mật khẩu cũ không được để trống")
    private String oldPassword;

    @NotEmpty(message = "Mật khẩu mới không được để trống")
    private String newPassword;

    @NotEmpty(message = "Nhập lại mật khẩu mới không được để trống")
    private String reNewPassword;
}
