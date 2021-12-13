package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReq {
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private int idCourse;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String content;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String ans1;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String ans2;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String ans3;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String ans4;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private int correctAns;

    private long createdTime;
}
