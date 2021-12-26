package com.example.demo.model.request.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateAccountRequest {
    @NotEmpty(message = "Email is required")
    @ApiModelProperty(
            example="ngocbich4686@gmail.com",
            notes="Email can't be empty",
            required=true
    )
    private String email;

    @NotEmpty(message = "Password is required")
    @ApiModelProperty(
            example="123abc",
            notes="Password can't be empty",
            required=true
    )
    private String password;

    @NotNull(message = "User name is required")
    @ApiModelProperty(
            example="darkcloud",
            notes="User name cannot be empty",
            required=true
    )
    private String username;

    private String fullName;

    private String phone;

    private String role;

    private Boolean isActivate;
}
