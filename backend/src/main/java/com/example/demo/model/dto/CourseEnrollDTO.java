package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CourseEnrollDTO {
    private Long id;

    @NotNull
    private Long CourseId;

    private List lessonPassed;

    private boolean coursePassed;
}
