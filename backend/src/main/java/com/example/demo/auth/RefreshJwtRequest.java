package com.example.demo.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RefreshJwtRequest {

    @NotNull(message = "required")
    private String refreshToken;

    @NotNull(message = "required")
    private String accessToken;

}
