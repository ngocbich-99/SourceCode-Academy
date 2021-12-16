package com.example.demo.service;

import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.SectionReq;

import java.util.List;

public interface SectionService {
    public SectionDTO addSection(SectionReq request);
    public SectionDTO addAllSection(List<SectionReq> requests);
}