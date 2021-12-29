package com.example.demo.controller;


import com.example.demo.constant.CommonConstant;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.section.UpdateSectionRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.LessonService;
import com.example.demo.service.SectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @ApiOperation(value = "Mask")
    @PostMapping
    public CloudResponse<String> markAsPass(@Valid Long lessonId){
        lessonService.markAsPass(lessonId);
        return CloudResponse.ok(CommonConstant.SUCCESS,"Mark as read");
    }

}