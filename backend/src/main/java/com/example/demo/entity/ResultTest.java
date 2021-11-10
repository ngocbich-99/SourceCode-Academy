package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity

public class ResultTest {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idRsTest;

    private int idTest;

    private int point;

    private int idStudent;

    private int totalCorrectQues;
}
