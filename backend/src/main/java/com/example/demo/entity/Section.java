package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity

public class Section {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idSection;

    @ManyToOne
    private int idCourse;

    private Date createdTime;

    private String nameSection;

}
