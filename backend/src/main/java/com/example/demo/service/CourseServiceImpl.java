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
        mapper.map(course, courseDTO);
        TypeToken<List<SectionDTO>> typeToken = new TypeToken<List<SectionDTO>>() {
        };
        courseDTO.setSections(sectionService.findAllByCourse(course));
        TypeToken<List<CategoryDTO>> categoryTokenType = new TypeToken<List<CategoryDTO>>() {
        };
        courseDTO.setCategory(mapper.map(course.getCategorys(), categoryTokenType.getType()));

        return courseDTO;
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CreateCourseReq courseReq) {

        Course course = new Course();
        if (courseReq.getCategoryIds() != null && !courseReq.getCategoryIds().isEmpty()) {
            course.setCategorys(this.getListCategory(courseReq.getCategoryIds()));
        }
        BeanUtils.copyProperties(courseReq, course);
        course = courseRepository.save(course);
        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCategory(mapper.map(course.getCategorys(), typeToken.getType()));
        BeanUtils.copyProperties(course, courseDTO);

        if (!courseReq.getSections().isEmpty() && courseReq.getSections() != null) {
            courseDTO.setSections(this.saveSectionList(courseReq.getSections(), course));
        }

        return courseDTO;

    }

    @Override
    public CourseDTO updateCourse(CreateCourseReq courseReq, int id) {
        Course course = courseRepository.findById(id).get();

        mapper.map(courseReq, course);
        BeanUtils.copyProperties(courseReq, course);

        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCategory(mapper.map(course.getCategorys(), typeToken.getType()));
        BeanUtils.copyProperties(course, courseDTO);

        if (!courseReq.getSections().isEmpty() && courseReq.getSections() != null) {
            courseDTO.setSections(this.saveSectionList(courseReq.getSections(), course));
        }
        course = courseRepository.save(course);

        return courseDTO;
        /**
         * must be throw exception
         */
//        return null;
    }

    @Override
    public void deleteCourse(int id) {
        try {
            Optional<Course> course = courseRepository.findById(id);
            if (course.isPresent()) {
                sectionService.deleteByCourse(course.get());
                courseRepository.delete(course.get());
            }
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
            if (!sectionReq.getLessons().isEmpty() && sectionReq.getLessons() != null) {
                sectionDTO.setLessons(this.saveLessonList(sectionReq.getLessons(), sectionDTO.getIdSection()));
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
