package com.example.demo.service;


import com.example.demo.entity.Course;
import com.example.demo.model.request.CreateCourseReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getAll();
    Course getCourseById(int id);
    Course createCourse(CreateCourseReq courseReq);
}
