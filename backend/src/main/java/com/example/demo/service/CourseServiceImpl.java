package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.CreateCourseReq;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.SectionRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    SectionService sectionService;

    @Autowired
    LessonService lessonService;

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
    @Transactional
    public Course createCourse(CreateCourseReq courseReq) {
        Course course = new Course();
        course.setIdTeacher(courseReq.getIdTeacher());
        course.setNameCourse(courseReq.getNameCourse());
        course.setCreatedTime(courseReq.getCreatedTime());
        course.setStatus(courseReq.getStatus());
        course.setLevel(courseReq.getLevel());
        courseRepository.save(course);

        return course;
//        Course course = new Course();
//        List<Section> sections = new ArrayList<>();
//        List<Lesson> lessons = new ArrayList<>();
//        TypeToken<List<Section>> sectionTypeToken = new TypeToken<List<Section>>() {
//        };
//        TypeToken<List<Lesson>> lessonTypeToken = new TypeToken<List<Lesson>>() {
//        };
//        BeanUtils.copyProperties(courseReq,course);
//        course = courseRepository.save(course);
//        sections = mapper.map(courseReq.getSectionSet(),sectionTypeToken.getType());
//
//        for(Section section : sections){
//            lessons = mapper.map(section.getListLesson(),lessonTypeToken.getType());
//            section.setCourse(course);
//            section = sectionRepository.save(section);
//            for(Lesson lesson : sections)
//        }

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
