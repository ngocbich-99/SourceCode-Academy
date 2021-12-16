package com.example.demo.model.request;

import com.example.demo.entity.Category;
import com.example.demo.entity.Question;
import com.example.demo.entity.Section;
import com.example.demo.entity.Student;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class CourseReq {

    private int idCourse;

    private int idTeacher;

    private List<Category> category;

    private String nameCourse;

    private long createdTime;

    private Boolean status;

    private int level;

    private String imgCover;

    private String description;

    private Set<Section> sectionSet;

}
