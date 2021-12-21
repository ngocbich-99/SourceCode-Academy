package com.example.demo.jwt;

import com.example.demo.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e)
            throws IOException, ServletException {

        String authErrorType = "";
        if (request.getAttribute(CommonConstant.AUTH_ERROR_TYPE) != null) {
            authErrorType = (String) request.getAttribute(CommonConstant.AUTH_ERROR_TYPE);
        }

        LOGGER.error("Unauthorized error. Message - {}", e.getMessage());
        if (CommonConstant.AUTH_ERROR_TOKEN_EXPIRE.equals(authErrorType)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    CommonConstant.AUTH_TOKEN_EXPIRE_MESSAGE);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    CommonConstant.NOT_LOGIN_MESSAGE);
        }
    }
}