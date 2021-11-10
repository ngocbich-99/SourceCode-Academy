package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity

public class Teacher {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idTeacher;

    @OneToOne
    private int idAccount;

    private int idCourse;

    private Date createdTime;
}
