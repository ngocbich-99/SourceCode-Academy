package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idComment;

    private int idCmtParent;

    @ManyToOne
    private Account account;

    private String content;

    private Date createdTime;

}
