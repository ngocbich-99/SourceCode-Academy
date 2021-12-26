package com.example.demo.model.request.question;

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

public class UpdateQuestionRequest {

    @NotNull(message = "id question is required")
    private Long id;

    @NotNull(message = " course id is required")
    private Long courseId;

    @NotEmpty(message = "content is required")
    private String content;

    @NotEmpty(message = "ans1 is required")
    private String ans1;

    @NotEmpty(message = "ans2 is required")
    private String ans2;

    @NotEmpty(message = "ans3 is required")
    private String ans3;

    @NotEmpty(message = "ans4 is required")
    private String ans4;

    @NotNull(message = "correctAns is required")
    private Integer correctAns;

    private Long createdTime;
}
