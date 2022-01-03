package com.example.demo.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
public class JwtSubject {

    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private String fullName;

    private String role;

    private String coursePass;

    public List<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }


}

