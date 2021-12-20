package com.example.demo.service;


import com.example.demo.entity.Course;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.course.CreateCourseRequest;
import com.example.demo.model.request.course.UpdateCourseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDTO> getAll();

    Course getCourseById(Long id);

    CourseDTO getCourseDTOById(Long id);

    CourseDTO createCourse(CreateCourseRequest request);

    CourseDTO updateCourse(UpdateCourseRequest request);

    void deleteCourse(Long id);
}
