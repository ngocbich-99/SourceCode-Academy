package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity

public class LessonPass {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idStudent;

    private int idLesson;

    private Date createdTime;

    private int percentLearned;
}
