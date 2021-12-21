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
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "lesson_id")
    Lesson lesson;

    private Long createdTime;

    private Integer percentLearned;
}
