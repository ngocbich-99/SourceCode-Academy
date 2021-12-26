package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.section.CreateSectionRequest;
import com.example.demo.model.request.section.UpdateSectionRequest;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.SectionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ModelMapper mapper;


    @Override
    public SectionDTO addSection(UpdateSectionRequest request) {
        Section section = new Section();
        Course course= courseService.getCourseById(request.getCourseId());
        BeanUtils.copyProperties(request,section);
        section.setCourse(course);
        Section sectionSaved = sectionRepository.save(section);
        SectionDTO sectionDTO = new SectionDTO();
        mapper.map(sectionSaved,sectionDTO);
        return sectionDTO;
    }

    @Override
    public SectionDTO addAllSection(List<UpdateSectionRequest> requests) {
        List<SectionDTO> sections = new ArrayList<>();
        return null;
    }

    @Override
    public SectionDTO addSection(CreateSectionRequest request, Course course) {
        Section section = new Section();
        BeanUtils.copyProperties(request,section);
        section.setCourse(course);
        Section sectionSaved = sectionRepository.save(section);
        SectionDTO sectionDTO = new SectionDTO();
        mapper.map(sectionSaved,sectionDTO);
        return sectionDTO;
    }

    @Override
    public SectionDTO updateSection(UpdateSectionRequest request, Course course) {
        Section section = new Section();
        BeanUtils.copyProperties(request,section);
        section.setCourse(course);
        Section sectionSaved = sectionRepository.save(section);
        SectionDTO sectionDTO = new SectionDTO();
        mapper.map(sectionSaved,sectionDTO);
        return sectionDTO;
    }

    @Override
    public void deleteByCourse(Course course) {
        List<Section> allByCourse = sectionRepository.findAllByCourse(course);
        allByCourse.stream().forEach(section -> lessonRepository.deleteAll(section.getLessons()));
        sectionRepository.deleteAll(allByCourse);
    }

    @Override
    public List<SectionDTO> findAllByCourse(Course course) {
        List<Section> sections = sectionRepository.findAllByCourse(course);
        List<SectionDTO> sectionDTOS = new ArrayList<>();
        for(Section section : sections){
            SectionDTO sectionDTO = new SectionDTO();
            mapper.map(section,sectionDTO);
            sectionDTO.setLessons(this.convertToListLessonDTO(section));
            sectionDTOS.add(sectionDTO);
        }
        return sectionDTOS;
    }

    private  List<LessonDTO> convertToListSectionDTO(Section section){
        TypeToken<List<SectionDTO>> typeToken = new TypeToken<List<SectionDTO>>(){};
        return mapper.map(lessonRepository.findAllBySection(section),typeToken.getType());
    }
    private  List<LessonDTO> convertToListLessonDTO(Section section){
        TypeToken<List<LessonDTO>> typeToken = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.findAllBySection(section),typeToken.getType());
    }
}
