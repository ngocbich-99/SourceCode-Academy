package com.example.demo.model.request.course;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EnrollRequest {

    @NotNull(message = "Id của khóa học không được để trống")
    private Long courseId;
}
