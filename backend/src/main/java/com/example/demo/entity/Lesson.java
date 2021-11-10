package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity

public class Lesson {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idLesson;

    @ManyToOne
    private int idSection;

    @OneToOne
    private int idTest;

    private Date createdTime;

    private String content; //video or test

    private String attachment;

}
