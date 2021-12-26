package com.example.demo.controller;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.auth.LoginRequest;
import com.example.demo.model.request.auth.RegisterAccountRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.model.response.user.LoginResponse;
import com.example.demo.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String DATA_SUCCESS = "Success";

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public CloudResponse<String> register(@Valid @RequestBody RegisterAccountRequest body) {
        accountService.reg(body);
        return CloudResponse.ok(DATA_SUCCESS,"Register Success !");
    }

    @PostMapping("/login")
    public CloudResponse<LoginResponse> login(@Valid @RequestBody LoginRequest body) throws JsonProcessingException {
        return CloudResponse.ok(accountService.login(body),"Register Success !");
    }
}
