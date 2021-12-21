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
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;

    private String name;

    private String urlVideo;

    private Long createdTime;

    private String type; //video or test

    private String description;

    @OneToMany(mappedBy = "lesson")
    Set<LessonPass> lessonPasses;
}