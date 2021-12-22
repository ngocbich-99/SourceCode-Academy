package com.example.demo.auth;

import javax.validation.constraints.NotNull;

public class RefreshJwtRequest {

    @NotNull(message = "required")
    private String refreshToken;
    @NotNull(message = "required")
    private String accessToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
