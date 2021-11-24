package com.example.demo.auth;

import com.example.demo.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    Account account;

    public CustomUserDetails() {
    }

    public CustomUserDetails(Account account) {
        this.account = account;
    }


    public Account getUser() {
        return account;
    }

    public void setUser(Account user) {
        this.account = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getAuthority();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.account.getIsActivate();
    }
}
