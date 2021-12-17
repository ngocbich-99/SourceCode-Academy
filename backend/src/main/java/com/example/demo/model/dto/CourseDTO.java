package com.example.demo.model.dto;

import com.example.demo.entity.Category;
import com.example.demo.entity.Question;
import com.example.demo.entity.Section;
import com.example.demo.entity.Student;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class CourseDTO {

    private int idCourse;

    private int idTeacher;

    private List<CategoryDTO> category;

    private String nameCourse;

    private long createdTime;

    private Boolean status;

    private int level;

    private String imgCover;

    private String description;

//    private Set<Student> studentSet;
//
//    private List<Question> questionList;

    private List<SectionDTO> sectionList;

}
