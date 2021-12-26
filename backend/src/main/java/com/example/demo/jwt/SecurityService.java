package com.example.demo.jwt;

import com.example.demo.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityService {

    @Value("${jwt.token.type}")
    private String tokenType;

    @Value("${header.Authorization}")
    private String keyHeaderAuthor;

    public String getToken(HttpServletRequest request) {
        String strTokenType = tokenType.concat(" ");
        String authHeader = request.getHeader(keyHeaderAuthor);
        if (authHeader != null && authHeader.startsWith(strTokenType)) {
            return authHeader.replace(strTokenType, "");
        }
        return null;
    }

    public String getToken(String authHeader) {
        String strTokenType = tokenType.concat(" ");
        if (authHeader != null && authHeader.startsWith(strTokenType)) {
            return authHeader.replace(strTokenType, "");
        }
        return null;
    }

    public void handleAuthorization(String username, String email) {

        UserPrinciple userPrinciple = getCurrentUser();

        if (userPrinciple != null) {
            boolean check = false;
            if (!userPrinciple.getEmail().isEmpty()) {
                check = userPrinciple.getEmail().equals(email);
            }
            if (!userPrinciple.getUsername().isEmpty()) {
                check = check || userPrinciple.getUsername().equals(username);
            }
            if (!check) {
                throw new RuntimeException("UnAuth");
            }
        }

    }

    public void handleAuthorization(String secondaryUsername) {
        handleAuthorization(secondaryUsername, "");
    }


    public UserPrinciple getCurrentUser() {
        return this.getCurrentUser(true);
    }

    public UserPrinciple getCurrentUser(boolean throwError) {
        UserPrinciple userPrinciple = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserPrinciple) {
                userPrinciple = (UserPrinciple) principal;
            }
        }

        if (userPrinciple == null && throwError) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    CommonConstant.NOT_LOGIN_MESSAGE);
        }
        return userPrinciple;
    }
}
