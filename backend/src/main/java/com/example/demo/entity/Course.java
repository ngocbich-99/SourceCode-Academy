package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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


    @ManyToMany
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categorys;

    @NotNull
    private String nameCourse;

    private long createdTime;

    @NotNull
    private Boolean status;

    @NotNull
    private int level;

    private String imgCover;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Question> questions;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Section> sections;

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }



    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }





    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }



    public long getCreatedTime() {
        return createdTime;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }


    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> studentSet) {
        this.students = studentSet;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questionList) {
        this.questions = questionList;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sectionSet) {
        this.sections = sectionSet;
    }
}
