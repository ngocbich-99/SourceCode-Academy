package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LessonPassKey implements Serializable {
    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "account_id")
    private Long accountId;
}
