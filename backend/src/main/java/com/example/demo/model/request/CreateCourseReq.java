package com.example.demo.model.request;

import com.example.demo.entity.Category;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CreateCourseReq {

    private int idCourse;

    private int idTeacher;

    private List<String> categoryIds;

    private String nameCourse;

    private long createdTime;

    private Boolean status;

    private int level;

    private String imgCover;

    private String description;

    private List<SectionReq> sectionList;
}
