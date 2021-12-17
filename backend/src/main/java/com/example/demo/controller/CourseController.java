package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.CourseDTO;
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
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getList() {
        return ResponseEntity.ok(courseService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }


    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CreateCourseReq courseReq) {
        return ResponseEntity.ok(courseService.createCourse(courseReq));
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCourse(@Valid @RequestBody CreateCourseReq req, @PathVariable int id) {
        Course course = courseService.updateCourse(req, id);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Delete category success");
    }

}
