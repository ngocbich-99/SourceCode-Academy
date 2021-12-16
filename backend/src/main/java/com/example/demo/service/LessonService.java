package com.example.demo.service;

import com.example.demo.entity.Lesson;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.LessonReq;

public interface LessonService {
    public LessonDTO addLesson(LessonReq request);
}
