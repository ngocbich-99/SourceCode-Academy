package com.example.demo.service;

import com.example.demo.entity.Section;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.lesson.CreateLessonRequest;
import com.example.demo.model.request.lesson.LessonPassRequest;
import com.example.demo.model.request.lesson.UpdateLessonRequest;

import java.util.List;

public interface LessonService {
    public LessonDTO addLesson(UpdateLessonRequest request);

    public List<LessonDTO> addLessons(List<CreateLessonRequest> request, Long sectionId);

    public List<LessonDTO> updateLessons(List<UpdateLessonRequest> request, Long sectionId);

    public List<LessonDTO> getLessons(Section section);

    public void markAsPass(LessonPassRequest request);
}
