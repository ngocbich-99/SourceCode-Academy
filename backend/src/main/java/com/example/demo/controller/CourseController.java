package com.example.demo.controller;

import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.course.CreateCourseRequest;
import com.example.demo.model.request.course.FindCourseByCategoriesRequest;
import com.example.demo.model.request.course.UpdateCourseRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.constant.CommonConstant.SUCCESS;

@RestController
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/list")
    public CloudResponse<List<CourseDTO>> getList() {
        return CloudResponse.ok(courseService.getAll());
    }


    @GetMapping("/{id}")
    public CloudResponse<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseDTOById(id);
        return CloudResponse.ok(course);
    }


    @PostMapping
    public CloudResponse<CourseDTO> createCourse(@Valid @RequestBody CreateCourseRequest courseReq) {
        return CloudResponse.ok(courseService.createCourse(courseReq));
    }

    @PutMapping
    public CloudResponse<CourseDTO> editCourse(@Valid @RequestBody UpdateCourseRequest body, @PathVariable int id) {
        return CloudResponse.ok(courseService.updateCourse(body));
    }

    @DeleteMapping("/delete/{id}")
    public CloudResponse<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return CloudResponse.ok(SUCCESS,"Delete category success");
    }

    @PostMapping("/categories")
    public CloudResponse<List<CourseDTO>> findCourseByCategories(@Valid @RequestBody FindCourseByCategoriesRequest body) {
        return CloudResponse.ok(courseService.findAllByCategoriesName(body));
    }


}
