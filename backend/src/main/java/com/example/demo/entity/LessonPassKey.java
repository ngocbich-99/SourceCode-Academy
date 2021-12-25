package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LessonPassKey implements Serializable {
    @Column(name = "lesson_id")
    int lessonId;

    @Column(name = "student_id")
    int studentId;
}
