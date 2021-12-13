package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idCourse;

    @NotNull
    private int idTeacher;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_category")
    private Category category;

    @NotNull
    private String nameCourse;

    private long createdTime;

    @NotNull
    private Boolean status;

    @NotNull
    private int level;

    private String imgCover;

    private String description;

    @ManyToMany
    private Set<Student> studentSet;

    @OneToMany(mappedBy = "course")
    private List<Question> questionList;

    @OneToMany
    private Set<Section> sectionSet;
}
