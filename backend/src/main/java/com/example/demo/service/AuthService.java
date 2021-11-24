package com.example.demo.service;

import com.example.demo.auth.LoginRequest;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.response.user.LoginResponse;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthService {

    private final AccountService userService;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AuthService(AccountService userService, UserDetailsServiceImpl userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Xu ly dang nhap.
     */
    public LoginResponse login(LoginRequest loginRequest) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
        final String token = jwtTokenProvider.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(token);
        return loginResponse;
    }

}
