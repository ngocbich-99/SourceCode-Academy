package com.example.demo.model.dto;

import com.example.demo.entity.LessonPass;
import com.example.demo.entity.Section;
import com.example.demo.entity.Test;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

public class LessonDTO {

    private int idLesson;

//    private Test test;

    private String urlVideo;

    private long createdTime;

    private String type;

    private String description;

}
