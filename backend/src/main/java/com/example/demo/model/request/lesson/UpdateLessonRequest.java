package com.example.demo.model.request.lesson;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateLessonRequest {

    @NotNull(message = "Id is required")
    private Long id;

    private Long createTime;

    private String description;

    private String urlVideo;

    private MultipartFile file;

    private String type;

    @NotNull(message = "Section id is required")
    private Long sectionId;

    private String name;
}
