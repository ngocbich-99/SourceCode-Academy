package com.example.demo.controller;

import com.example.demo.auth.LoginRequest;
import com.example.demo.model.response.user.LoginResponse;
import com.example.demo.service.AccountService;

import com.example.demo.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Log4j2
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final AccountService userService;

    private final AuthService authService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    AccountService userService,
                                    AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.authService = authService;
    }


    /**
     * Xác thực người dùng và cấp phát JWT.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAdmin() {
        return new ResponseEntity<>("Administrator", HttpStatus.OK);
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity getManager() {
        return new ResponseEntity<>("Manager", HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getUser() {
        return new ResponseEntity<>("User", HttpStatus.OK);
    }

    /**
     * Hàm xác tự người dùng.
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
