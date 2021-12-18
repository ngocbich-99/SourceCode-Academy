package com.example.demo.model.request;

import lombok.Data;

import java.util.List;


@Data
public class SectionReq {
    private int idSection;
    private int courseId;
    private long createTime;
    private String sectionName;
    private int level;
    private List<LessonReq> lessons;
}
