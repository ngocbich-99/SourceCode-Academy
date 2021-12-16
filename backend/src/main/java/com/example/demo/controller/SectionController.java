package com.example.demo.controller;

import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.SectionReq;
import com.example.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionDTO> addSection(SectionReq request){
        return ResponseEntity.ok(sectionService.addSection(request));
    }

}
