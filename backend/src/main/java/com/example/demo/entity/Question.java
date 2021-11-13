package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity

public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idQuestion;

    @ManyToOne
    private Course course;

    private String content;

    private String ans1;

    private String ans2;

    private String ans3;

    private String ans4;

    private int correctAns;

    private Date createdTime;

}
