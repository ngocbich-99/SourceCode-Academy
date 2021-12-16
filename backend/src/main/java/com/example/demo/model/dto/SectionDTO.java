package com.example.demo.model.dto;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class SectionDTO {

    private int idSection;

    private long createdTime;

    private String nameSection;

    private List<Lesson> listLesson;
}
