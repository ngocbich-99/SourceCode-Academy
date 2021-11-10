package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity

public class ResultTest {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idRsTest;

    @ManyToOne
    private int idStudent;

    private int idTest;

    private int point;

    private int totalCorrectQues;
}
