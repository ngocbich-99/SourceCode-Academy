package com.example.demo.entity;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String fullName;

    @NotNull
    private String password;

    private String username;

    private String phone;

    private Boolean isActivate;

    @Column(name="role", columnDefinition = "varchar(25) default 'HOC_VIEN'")
    private String role;

    private Long createdTime;

    @OneToMany
    private List<Comment> comments;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    Set<LessonPass> lessonPasses;

    @Transient
    public List<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }


}
