package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
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
    public CourseDTO createCourse(CreateCourseReq courseReq) {

        Course course = new Course();
        List<Section> sectionList = new ArrayList<>();
        List<Lesson> lessonList = new ArrayList<>();
        TypeToken<List<Section>> sectionTypeToken = new TypeToken<List<Section>>() {
        };
        TypeToken<List<Lesson>> lessonTypeToken = new TypeToken<List<Lesson>>() {
        };
        BeanUtils.copyProperties(courseReq,course);
        course = courseRepository.save(course);

//        for(SectionReq sectionReq : courseReq.getSectionSet()){
//            SectionDTO sectionDTO = sectionService.addSection(sectionReq);
//            sectionDTO.setListLesson(this.saveLessonList(sectionReq.getListLesson(),sectionDTO.getIdSection()));
//        }
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course,courseDTO);
        courseDTO.setSectionList(this.saveSectionList(courseReq.getSectionList(),course));
        return courseDTO;

    }

    @Override
    public Course updateCourse(CreateCourseReq req, int id) {
        Course course = courseRepository.findById(id).get();
        course.setIdTeacher(req.getIdTeacher());
        course.setLevel(req.getLevel());
        course.setStatus(req.getStatus());
        List<Category> categories = course.getCategory();
//        categories.add(categoryRepository.findById(req.getIdCategory()).get());
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

    private List<LessonDTO> saveLessonList(List<LessonReq> lessonReqs, Integer sectionId){
        return lessonService.addLessons(lessonReqs,sectionId);
    }
    private List<SectionDTO> saveSectionList(List<SectionReq> sectionReqs,Course course){
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for(SectionReq sectionReq : sectionReqs){
            SectionDTO sectionDTO = sectionService.addSection(sectionReq, course);
            sectionDTO.setListLesson(this.saveLessonList(sectionReq.getListLesson(),sectionDTO.getIdSection()));
            sectionDTOList.add(sectionDTO);
        }
        return sectionDTOList;
    }
}
