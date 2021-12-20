package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    private String content;

    private String ans1;

    private String ans2;

    private String ans3;

    private String ans4;

    private int correctAns;

    private long createdTime;

    @ManyToMany
    private Collection<Test> tests;

}
