package com.example.demo.model.request.section;

import com.example.demo.model.request.lesson.CreateLessonRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class CreateSectionRequest {

    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Course id is required")
    private Long courseId;

    private Long createTime;

    @NotEmpty(message = "Section name id is required")
    private String name;

    private int level;

    private List<CreateLessonRequest> lessons;
}
