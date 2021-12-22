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
    @NotNull(message = "id course is required")
    private int idCourse;

    @NotNull(message = "content is required")
    @NotEmpty(message = "content is required")
    private String content;

    @NotNull(message = "ans1 is required")
    @NotEmpty(message = "ans1 is required")
    private String ans1;

    @NotNull(message = "ans2 is required")
    @NotEmpty(message = "ans2 is required")
    private String ans2;

    @NotNull(message = "ans3 is required")
    @NotEmpty(message = "ans3 is required")
    private String ans3;

    @NotNull(message = "ans4 is required")
    @NotEmpty(message = "ans4 is required")
    private String ans4;

    @NotNull(message = "correctAns is required")
    private int correctAns;

    private long createdTime;
}
