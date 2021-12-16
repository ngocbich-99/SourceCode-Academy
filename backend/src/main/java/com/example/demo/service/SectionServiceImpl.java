package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.SectionReq;
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
    ModelMapper mapper;


    @Override
    public SectionDTO addSection(SectionReq request) {
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
    public SectionDTO addAllSection(List<SectionReq> requests) {
        List<SectionDTO> sections = new ArrayList<>();
        return null;
    }
}
