package com.example.demo.model.dto;

import lombok.Data;

@Data
public class LessonDTO {

    private Long id;

    private String name;

    private String urlVideo;

    private Long createdTime;

    private String type;

    private String description;

}
