package com.example.demo.model.request.lesson;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateLessonRequest {

    private Long createTime;

    private String description;

    private String urlVideo;

    private MultipartFile file;

    @NotNull(message = "Section id is required")
    private Long sectionId;

    private String type;

    private String name;
}
