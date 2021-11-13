package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity

public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idStudent;

    @OneToOne
    private Account account;

    private Date createdTime;

    @ManyToMany
    private Set<Course> courseSet;

    @OneToMany
    private Set<ResultTest> resultTestSet;

    @OneToMany(mappedBy = "student")
    Set<LessonPass> lessonPasses;
}
