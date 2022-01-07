package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class CourseEnroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long courseId;

    /**
     * Json object lesson passed
     */
    private String lessonPassed = "[]";

    @ManyToOne
    private Account account;

    private boolean coursePassed;
}
