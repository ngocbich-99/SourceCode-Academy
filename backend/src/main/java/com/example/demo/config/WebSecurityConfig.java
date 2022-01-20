package com.example.demo.config;

import com.example.demo.constant.CommonConstant;
import com.example.demo.constant.RoleConstant;
import com.example.demo.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.jwt.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint authenticationHandler;

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.csrf().disable();
        http.cors();
        String[] pathsNoAuth = new String[CommonConstant.PATHS_NO_AUTHENTICATION.size()];
        CommonConstant.PATHS_NO_AUTHENTICATION.toArray(pathsNoAuth);
        http.authorizeRequests()
                .antMatchers("/api/accounts/**").hasAnyAuthority(RoleConstant.ADMIN, RoleConstant.GIANG_VIEN) // tat ca api account deu phai can co quyen admin
                .antMatchers(pathsNoAuth).permitAll() // ko can token
                .anyRequest().authenticated() // nhung request con lai khong thuoc 2 cai tren deu can token
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationHandler)// xu ly exception khi co authenticated
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

}
