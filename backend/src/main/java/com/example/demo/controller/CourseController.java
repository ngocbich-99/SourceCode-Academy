package com.example.demo.controller;

import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.course.CreateCourseRequest;
import com.example.demo.model.request.course.UpdateCourseRequest;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseDTOById(id);
        return ResponseEntity.ok(course);
    }


    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CreateCourseRequest courseReq) {
        return ResponseEntity.ok(courseService.createCourse(courseReq));
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<CourseDTO> editCourse(@Valid @RequestBody UpdateCourseRequest body, @PathVariable int id) {
        return ResponseEntity.ok(courseService.updateCourse(body));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Delete category success");
    }

}
