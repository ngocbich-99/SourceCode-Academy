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
}
