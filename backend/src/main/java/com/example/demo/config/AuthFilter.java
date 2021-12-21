package com.example.demo.config;


import com.example.demo.jwt.JwtProvider;
import com.example.demo.jwt.JwtSubject;
import com.example.demo.jwt.UserPrinciple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider tokenProvider;


    @Value("${jwt.token.type}")
    private String tokenType;

    @Value("${header.Authorization}")
    private String keyHeaderAuthor;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getToken(request);
            if (token != null && tokenProvider.validateJwtToken(token, request)) {
                JwtSubject jwtSubject = tokenProvider.getSubjectFromJwtToken(token);
                UserDetails userDetails = new UserPrinciple();
                BeanUtils.copyProperties(jwtSubject, userDetails);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            LOGGER.error("Can NOT set user authentication -> Message: {}", e.getMessage());
            LOGGER.debug("Can NOT set user authentication -> error: ", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String strTokenType = tokenType.concat(" ");
        String authHeader = request.getHeader(keyHeaderAuthor);
        if (authHeader != null && authHeader.startsWith(strTokenType)) {
            return authHeader.replace(strTokenType, "");
        }
        return null;
    }
}
