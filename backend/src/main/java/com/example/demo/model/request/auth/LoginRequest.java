package com.example.demo.model.request.auth;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString(exclude = "password")
@Data
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "Tên đăng nhập không được để trống")
    private String username;

    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;

    private String deviceId;

    private String deviceToken;

    /**
     * 1 IOS 2 Android
     */
    private String os;


}
