package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.request.CategoryReq;
import com.example.demo.model.request.CreateAccountReq;
import com.example.demo.model.request.CreateCourseReq;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CreateCourseReq courseReq) {

        Course course = courseService.createCourse(courseReq);
        return ResponseEntity.ok(course);
    }


    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCourse(@Valid @RequestBody CreateCourseReq req, @PathVariable int id) {
        Course course = courseService.updateCourse(req, id);
        return ResponseEntity.ok(course);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Delete category success");
    }

}
