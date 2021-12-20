package com.example.demo.model.request.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateAccountRequest {

    @NotNull(message = "Id is required")
    private Long id;

    @NotEmpty(message = "Email is required")
    @ApiModelProperty(
            example = "ngocbich4686@gmail.com",
            notes = "Email can't be empty",
            required = true
    )
    private String email;

    @NotEmpty(message = "Password is required")
    @ApiModelProperty(
            example = "123abc",
            notes = "Password can't be empty",
            required = true
    )
    private String password;

    @NotNull(message = "User name is required")
    @ApiModelProperty(
            example = "Nguyen Thi Ngoc Bich",
            notes = "User name cannot be empty",
            required = true
    )
    private String userName;

    private String phone;

    @ApiModelProperty(
            example = "HOC_VIEN",
            notes = "HOC_VIEN is default role"
    )
    private String role;

    private Long createdTime;

    private Boolean isActivate;
}
