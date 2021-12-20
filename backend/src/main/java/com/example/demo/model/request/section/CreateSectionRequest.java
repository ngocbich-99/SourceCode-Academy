package com.example.demo.model.request.section;

import com.example.demo.model.request.lesson.CreateLessonRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class CreateSectionRequest {

    @NotNull(message = "Course id is required")
    private Long courseId;

    private Long createTime;

    @NotEmpty(message = "Section name id is required")
    private String name;

    private List<CreateLessonRequest> lessons;
}
