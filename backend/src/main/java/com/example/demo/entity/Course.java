package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idCourse;

    private int idTeacher;

    @ManyToOne
    private Category category;

    private String nameCourse;

    private Date createdTime;

    private Boolean status;

    private int level;

    private String imgCover;

    private String description;

    @ManyToMany
    private Set<Student> studentSet;

    @OneToMany
    private Set<Question> questionSet;

    @OneToMany
    private Set<Section> sectionSet;
}
