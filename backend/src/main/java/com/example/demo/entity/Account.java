package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.demo.constant.RoleConstant.HOC_VIEN;

@Getter
@Setter
@Entity
@DynamicInsert
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String fullName;

    private String password;

    private String username;

    private String phone;

    private Boolean isActivate;

    private String role = HOC_VIEN;

    private Long createdTime;

//    @OneToMany
//    private List<Comment> comments;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    Set<LessonPass> lessonPasses;

    @Transient
    public List<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }


}
