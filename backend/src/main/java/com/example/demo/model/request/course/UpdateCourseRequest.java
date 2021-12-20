package com.example.demo.model.request.course;

import com.example.demo.model.request.section.CreateSectionRequest;
import com.example.demo.model.request.section.UpdateSectionRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateCourseRequest {

    @NotNull(message = "course Id is required")
    private Long id;

    @NotNull(message = "teacher Id is required")
    private Long teacherId;

    @NotNull(message = "categoryIds Id is required")
    private List<Long> categoryIds;

    @NotEmpty(message = "name is required")
    private String name;

    private Long createdTime;

    @NotNull(message = "status is required")
    private Boolean status;

    @NotNull(message = "level is required")
    private Integer level;

    @NotEmpty(message = "Image cover is required")
    private String imgCover;

    private String description;


    private List<CreateSectionRequest> sections;

}
