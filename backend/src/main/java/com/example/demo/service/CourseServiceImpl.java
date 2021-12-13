package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.model.request.CreateCourseReq;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course createCourse(CreateCourseReq courseReq) {
        Course course = new Course();
        course.setIdTeacher(courseReq.getIdTeacher());
        course.setNameCourse(courseReq.getNameCourse());
        course.setCreatedTime(courseReq.getCreatedTime());
        course.setStatus(courseReq.getStatus());
        course.setLevel(courseReq.getLevel());
        courseRepository.save(course);

        return course;
    }
}
