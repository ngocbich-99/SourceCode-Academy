package com.example.demo.entity;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name="role", columnDefinition = "varchar(255) default 'HOC_VIEN'")
    private String role;

    private Date createdTime;

    @OneToMany
    private Set<Comment> commentSet;


}
