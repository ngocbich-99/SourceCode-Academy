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

public class Teacher {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idTeacher;

    private int idAccount;

    private int idCourse;

    private Date createdTime;
}
