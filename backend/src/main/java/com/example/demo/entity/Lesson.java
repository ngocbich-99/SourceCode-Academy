package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity

public class Lesson {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idLesson;

    @ManyToOne
    private Section section;

    @OneToOne
    private Test test;

    private Date createdTime;

    private String content; //video or test

    private String attachment;

    @OneToMany(mappedBy = "lesson")
    Set<LessonPass> lessonPasses;
}
