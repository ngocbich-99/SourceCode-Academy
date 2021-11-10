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

public class Lesson {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idLesson;

    private int idSection;

    private int idTest;

    private Date createdTime;

    private String content; //video or test

    private String attachment;

}
