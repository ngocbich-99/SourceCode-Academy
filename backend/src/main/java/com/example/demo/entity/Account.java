package com.example.demo.entity;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAccount;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String userName;

    private String phone;

    private Boolean isActivate;

    @Column(name="role", columnDefinition = "varchar(255) default 'HOC_VIEN'")
    private String role;

    private Date createdTime;

    @OneToMany
    private Set<Comment> commentSet;

    @Transient
    public List<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }


}
