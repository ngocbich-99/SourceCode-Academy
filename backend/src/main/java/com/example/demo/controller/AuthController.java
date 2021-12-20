package com.example.demo.controller;

import com.example.demo.model.dto.AccountDTO;
import com.example.demo.model.request.account.CreateAccountRequest;
import com.example.demo.model.request.auth.RegisterAccountRequest;
import com.example.demo.model.response.CloudResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    private static final String DATA_SUCCESS = "Success";

    @PostMapping("/register")
    public CloudResponse<String> register(@Valid @RequestBody RegisterAccountRequest body) {
        return CloudResponse.ok(DATA_SUCCESS,"Register Success !");
    }
}
