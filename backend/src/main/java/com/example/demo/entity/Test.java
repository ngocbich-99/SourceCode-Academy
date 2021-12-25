package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

public class Test {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idTest;

    private int idCourse;

    private long createdTime;

    private String nameTest;

    private int level;

    private long timeTest;

    private int totalOfQues;

    @ManyToMany
    private Set<Question> questionSet;
}
