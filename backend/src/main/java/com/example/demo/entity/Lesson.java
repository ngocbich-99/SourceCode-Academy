package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_section")
    private Section section;

    @OneToOne
    private Test test;

    private String urlVideo;

    private long createdTime;

    private String content; //video or test

    private String description;

    @OneToMany(mappedBy = "lesson")
    Set<LessonPass> lessonPasses;
}
