package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idQuestion;

    @ManyToOne
    @JoinColumn(name="id_course")
    private Course course;

    private String content;

    private String ans1;

    private String ans2;

    private String ans3;

    private String ans4;

    private int correctAns;

    private long createdTime;

    @ManyToMany(mappedBy = "questions")
    private List<Test> tests;

}
