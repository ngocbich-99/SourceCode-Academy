package com.example.demo.service;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.LessonReq;
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

    @Override
    public List<LessonDTO> addLessons(List<LessonReq> requests,Integer sectionId) {
        Section section = this.findSectionById(sectionId);
        TypeToken<List<Lesson>> typeToken = new TypeToken<List<Lesson>>(){};
        List<Lesson> lessonList = mapper.map(requests,typeToken.getType());
        for(Lesson lesson : lessonList){
            lesson.setSection(section);
        }
        TypeToken<List<LessonDTO>> typeTokenDTO = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.saveAll(lessonList),typeTokenDTO.getType());
    }


    private Section findSectionById(Integer id){
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
