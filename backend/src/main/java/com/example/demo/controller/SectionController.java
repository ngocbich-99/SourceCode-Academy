package com.example.demo.controller;

import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.SectionReq;
import com.example.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionDTO> addSection(@Valid @RequestBody SectionReq request){
        return ResponseEntity.ok(sectionService.addSection(request));
    }

}