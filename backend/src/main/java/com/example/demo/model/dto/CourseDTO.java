package com.example.demo.model.dto;

import com.example.demo.entity.Question;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {

    private Long id;

    private Long teacherId;

    private List<CategoryDTO> categories;

    private String name;

    private Long createdTime;

    private Boolean status;

    private int level;

    private String imgCover;

    private String description;

    private List<Question> questions;

    private List<SectionDTO> sections;

    private Long subscriberNumber;

}
