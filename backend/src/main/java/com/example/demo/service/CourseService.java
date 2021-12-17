package com.example.demo.service;


import com.example.demo.entity.Course;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.CreateCourseReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDTO> getAll();

    Course getCourseById(int id);

    CourseDTO createCourse(CreateCourseReq courseReq);

    Course updateCourse(CreateCourseReq req, int id);

    void deleteCourse(int id);
}
