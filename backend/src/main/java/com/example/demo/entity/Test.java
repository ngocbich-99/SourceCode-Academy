package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @JoinTable(
            name="question-test",
            joinColumns = @JoinColumn(name="id_question"),
            inverseJoinColumns = @JoinColumn(name = "id_test")
    )
    private List<Question> questions;
}
