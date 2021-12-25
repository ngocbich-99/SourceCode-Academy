package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class LessonPass {
    @EmbeddedId
    LessonPassKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "lesson_id")
    Lesson lesson;

    private long createdTime;

    private int percentLearned;
}
