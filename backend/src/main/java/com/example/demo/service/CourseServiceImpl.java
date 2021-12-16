package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.CreateCourseReq;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CourseRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<CourseDTO> getAll() {
        List<Course> all = courseRepository.findAll();
        TypeToken<List<CourseDTO>> typeToken = new TypeToken<List<CourseDTO>>() {
        };
        return mapper.map(all,typeToken.getType());
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

    @Override
    public Course updateCourse(CreateCourseReq req, int id) {
        Course course = courseRepository.findById(id).get();
        course.setIdTeacher(req.getIdTeacher());
        course.setLevel(req.getLevel());
        course.setStatus(req.getStatus());
        List<Category> categories = course.getCategory();
        categories.add(categoryRepository.findById(req.getIdCategory()).get());
        course.setCategory(categories);
        course.setNameCourse(req.getNameCourse());
        courseRepository.save(course);
        return course;
    }

    @Override
    public void deleteCourse(int id) {
        try {
            courseRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalException("Db error. Can't delete course");
        }
    }
}
