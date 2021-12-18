package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.CreateCourseReq;
import com.example.demo.model.request.LessonReq;
import com.example.demo.model.request.SectionReq;
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
import java.util.Optional;

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
    CategoryService categoryService;

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
        return mapper.map(all, typeToken.getType());
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public CourseDTO getCourseDTOById(Integer id) {
        CourseDTO courseDTO = new CourseDTO();
        Course course = this.getCourseById(id);
        TypeToken<List<SectionDTO>> typeToken = new TypeToken<List<SectionDTO>>(){};
        courseDTO.setSectionList(mapper.map(sectionRepository.findAllByCourse(course),typeToken.getType()));

        TypeToken<List<CategoryDTO>> categoryTokenType = new TypeToken<List<CategoryDTO>>(){};
        courseDTO.setSectionList(mapper.map(sectionRepository.findAllByCourse(course),typeToken.getType()));
        courseDTO.setCategory(mapper.map(course.getCategory(),categoryTokenType.getType()));
        mapper.map(course,courseDTO);
        return courseDTO;
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CreateCourseReq courseReq) {

        Course course = new Course();
        if (courseReq.getCategoryIds() != null && !courseReq.getCategoryIds().isEmpty()) {
            course.setCategory(this.getListCategory(courseReq.getCategoryIds()));
        }
        BeanUtils.copyProperties(courseReq, course);
        course = courseRepository.save(course);
        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCategory(mapper.map(course.getCategory(), typeToken.getType()));
        BeanUtils.copyProperties(course, courseDTO);

        if (!courseReq.getSectionList().isEmpty() && courseReq.getSectionList() != null) {
            courseDTO.setSectionList(this.saveSectionList(courseReq.getSectionList(), course));
        }

        return courseDTO;

    }

    @Override
    public Course updateCourse(CreateCourseReq req, int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            course.get().setIdTeacher(req.getIdTeacher());
            course.get().setLevel(req.getLevel());
            course.get().setStatus(req.getStatus());
            List<Category> categories = course.get().getCategory();
            course.get().setCategory(categories);
            course.get().setNameCourse(req.getNameCourse());
            return courseRepository.save(course.get());
        }
        /**
         * must be throw exception
         */
        return null;
    }

    @Override
    public void deleteCourse(int id) {
        try {
            courseRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalException("Db error. Can't delete course");
        }
    }

    private List<LessonDTO> saveLessonList(List<LessonReq> lessonReqs, Integer sectionId) {
        return lessonService.addLessons(lessonReqs, sectionId);
    }

    private List<SectionDTO> saveSectionList(List<SectionReq> sectionReqs, Course course) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionReq sectionReq : sectionReqs) {
            SectionDTO sectionDTO = sectionService.addSection(sectionReq, course);
            if (sectionReq.getListLesson().isEmpty() && sectionReq.getListLesson() != null) {
                sectionDTO.setListLesson(this.saveLessonList(sectionReq.getListLesson(), sectionDTO.getIdSection()));
            }
            sectionDTOList.add(sectionDTO);
        }
        return sectionDTOList;
    }

    private List<Category> getListCategory(List<Integer> ids) {
        List<Category> categoryList = new ArrayList<>();
        for (Integer id : ids) {
            categoryList.add(categoryService.getCategoryById(id));
        }
        return categoryList;
    }
}
