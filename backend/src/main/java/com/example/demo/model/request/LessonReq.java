package com.example.demo.model.request;

import lombok.Data;

@Data
public class LessonReq {
    private long createTime;
    private String description;
    private String urlVideo;
    private int idSection;
}
