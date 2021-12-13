package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

}
