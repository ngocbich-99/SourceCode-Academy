package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.section.CreateSectionRequest;
import com.example.demo.model.request.section.UpdateSectionRequest;

import java.util.List;

public interface SectionService {
    public SectionDTO addSection(UpdateSectionRequest request);

    public SectionDTO addAllSection(List<UpdateSectionRequest> requests);

    public SectionDTO addSection(CreateSectionRequest request, Course course);

    public SectionDTO updateSection(UpdateSectionRequest request, Course course);

    public void deleteByCourse(Course course);

    public List<SectionDTO> findAllByCourse(Course course);

}
