package com.example.demo.model.response.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

public class UserRoleResponse {
    private UUID userId;
    private String username;
    private List<GrantedAuthority> listRole;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GrantedAuthority> getListRole() {
        return listRole;
    }

    public void setListRole(List<GrantedAuthority> listRole) {
        this.listRole = listRole;
    }
}
