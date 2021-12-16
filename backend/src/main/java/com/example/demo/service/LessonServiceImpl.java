package com.example.demo.service;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.LessonReq;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public LessonDTO addLesson(LessonReq request) {
        Optional<Section> section = sectionRepository.findById(request.getIdSection());
        if (section.isPresent()) {
            Lesson lesson = new Lesson();
            BeanUtils.copyProperties(request, lesson);
            lesson.setSection(section.get());
            lessonRepository.save(lesson);
        }
        //throw exception
        return null;
    }
}
