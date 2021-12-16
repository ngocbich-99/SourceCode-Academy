package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
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
    private List<Category> category;

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
    private Set<Student> studentSet;

    @OneToMany(mappedBy = "course")
    private List<Question> questionList;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Section> sectionSet;

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


    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Set<Section> getSectionSet() {
        return sectionSet;
    }

    public void setSectionSet(Set<Section> sectionSet) {
        this.sectionSet = sectionSet;
    }
}
