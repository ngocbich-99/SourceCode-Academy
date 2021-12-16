package com.example.demo.model.request;

import lombok.Data;

@Data
public class LessonReq {
    private int idLesson;
    private long createTime;
    private String description;
    private String urlVideo;
    private String idSection;
}
