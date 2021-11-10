package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CreateAccountReq {
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @ApiModelProperty(
            example="ngocbich4686@gmail.com",
            notes="Email can't be empty",
            required=true
    )
    private String email;

    @NotNull(message = "Password is required")
    @ApiModelProperty(
            example="123abc",
            notes="Password can't be empty",
            required=true
    )
    private String password;

    @NotNull(message = "User name is required")
    @ApiModelProperty(
            example="Nguyen Thi Ngoc Bich",
            notes="User name cannot be empty",
            required=true
    )
    private String userName;

    private String phone;

    private String role;

    private Date createdTime;
}
