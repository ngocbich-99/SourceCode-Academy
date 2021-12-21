package com.example.demo.service;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.lesson.CreateLessonRequest;
import com.example.demo.model.request.lesson.UpdateLessonRequest;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.SectionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public LessonDTO addLesson(UpdateLessonRequest request) {
        Optional<Section> section = sectionRepository.findById(request.getSectionId());
        if (section.isPresent()) {
            Lesson lesson = new Lesson();
            BeanUtils.copyProperties(request, lesson);
            lesson.setSection(section.get());
            lessonRepository.save(lesson);
        }
        //throw exception
        return null;
    }

    @Override
    public List<LessonDTO> addLessons(List<CreateLessonRequest> requests, Long sectionId) {
        Section section = this.findSectionById(sectionId);
        TypeToken<List<Lesson>> typeToken = new TypeToken<List<Lesson>>(){};
        List<Lesson> lessonList = mapper.map(requests,typeToken.getType());
        for(Lesson lesson : lessonList){
            lesson.setSection(section);
        }
        TypeToken<List<LessonDTO>> typeTokenDTO = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.saveAll(lessonList),typeTokenDTO.getType());
    }

    @Override
    public List<LessonDTO> getLessons(Section section) {
        TypeToken<List<LessonDTO>> typeToken = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.findAllBySection(section),typeToken.getType());
    }


    private Section findSectionById(Long id){
        Optional<Section> section = sectionRepository.findById(id);
        if(section.isPresent()){
            return section.get();
        }
        /**
         * Will throw exception in future
         */
        return null;
    }

}
