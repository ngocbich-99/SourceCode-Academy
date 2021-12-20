package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.course.CreateCourseRequest;
import com.example.demo.model.request.course.FindCourseByCategoriesRequest;
import com.example.demo.model.request.course.UpdateCourseRequest;
import com.example.demo.model.request.lesson.CreateLessonRequest;
import com.example.demo.model.request.section.CreateSectionRequest;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }


    @Override
    public CourseDTO getCourseDTOById(Long id) {
        CourseDTO courseDTO = new CourseDTO();
        Course course = this.getCourseById(id);
        mapper.map(course, courseDTO);
        TypeToken<List<SectionDTO>> typeToken = new TypeToken<List<SectionDTO>>() {
        };
        courseDTO.setSections(sectionService.findAllByCourse(course));
        TypeToken<List<CategoryDTO>> categoryTokenType = new TypeToken<List<CategoryDTO>>() {
        };
        courseDTO.setCategories(mapper.map(course.getCategories(), categoryTokenType.getType()));

        return courseDTO;
    }

    @Override
    public List<CourseDTO> findAllByCategoriesName(FindCourseByCategoriesRequest request) {
//        return this.convertToListCourseDTO(courseRepository
//                .findAllByCategories(request.getCategories())
//        );
        return null;
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CreateCourseRequest request) {

        Course course = new Course();
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            course.setCategories(this.getListCategory(request.getCategoryIds()));
        }
        BeanUtils.copyProperties(request, course);
        course = courseRepository.save(course);
        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCategories(mapper.map(course.getCategories(), typeToken.getType()));
        BeanUtils.copyProperties(course, courseDTO);

        if (!request.getSections().isEmpty() && request.getSections() != null) {
            courseDTO.setSections(this.saveSectionList(request.getSections(), course));
        }

        return courseDTO;

    }

    @Override
    public CourseDTO updateCourse(UpdateCourseRequest request) {
        Course course = courseRepository.findById(request.getId()).get();

        mapper.map(request, course);
        BeanUtils.copyProperties(request, course);

        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCategories(mapper.map(course.getCategories(), typeToken.getType()));
        BeanUtils.copyProperties(course, courseDTO);

        if (!request.getSections().isEmpty() && request.getSections() != null) {
            courseDTO.setSections(this.saveSectionList(request.getSections(), course));
        }
        course = courseRepository.save(course);

        return courseDTO;
        /**
         * must be throw exception
         */
//        return null;
    }

    @Override
    public void deleteCourse(Long id) {
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

    private List<LessonDTO> saveLessonList(List<CreateLessonRequest> requests, Long sectionId) {
        return lessonService.addLessons(requests, sectionId);
    }

    private List<SectionDTO> saveSectionList(List<CreateSectionRequest> requests, Course course) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();

        for (CreateSectionRequest sectionReq : requests) {
            SectionDTO sectionDTO = sectionService.addSection(sectionReq, course);
            if (!sectionReq.getLessons().isEmpty() && sectionReq.getLessons() != null) {
                sectionDTO.setLessons(this.saveLessonList(sectionReq.getLessons(), sectionDTO.getId()));
            }
            sectionDTOList.add(sectionDTO);
        }
        return sectionDTOList;
    }

    private List<Category> getListCategory(List<Long> ids) {
//        List<Category> categoryList = new ArrayList<>();
//        for (Long id : ids) {
//            categoryList.add(categoryService.getCategoryById(id));
//        }
        return categoryService.getCategoriesInIds(ids);
    }

    private List<CourseDTO> convertToListCourseDTO(List<Course> courses) {
        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        return mapper.map(courses, typeToken.getType());
    }
}
