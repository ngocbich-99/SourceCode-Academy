package com.example.demo.service;

import com.example.demo.entity.Lesson;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.LessonReq;

import java.util.List;

public interface LessonService {
    public LessonDTO addLesson(LessonReq request);

    public List<LessonDTO> addLessons(List<LessonReq> request,Integer sectionId);

}
