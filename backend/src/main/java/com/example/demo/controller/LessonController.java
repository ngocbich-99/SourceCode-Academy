package com.example.demo.controller;


import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.section.UpdateSectionRequest;
import com.example.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/lesson")
public class LessonController {


    @PostMapping
    public ResponseEntity<SectionDTO> addSection(@Valid @RequestBody UpdateSectionRequest request){
        return ResponseEntity.ok(null);
    }

}