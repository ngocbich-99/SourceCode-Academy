package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import com.example.demo.jwt.SecurityService;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.dto.SectionDTO;
import com.example.demo.model.request.course.CreateCourseRequest;
import com.example.demo.model.request.course.EnrollRequest;
import com.example.demo.model.request.course.FindCourseByCategoriesRequest;
import com.example.demo.model.request.course.UpdateCourseRequest;
import com.example.demo.model.request.lesson.CreateLessonRequest;
import com.example.demo.model.request.lesson.UpdateLessonRequest;
import com.example.demo.model.request.section.CreateSectionRequest;
import com.example.demo.model.request.section.UpdateSectionRequest;
import com.example.demo.repository.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    private SecurityService securityService;

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
        LOGGER.info("getCourseDTOById : {}",id);
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
        LOGGER.info("findAllByCategoriesName : {}",request);
        return this.convertToListCourseDTO(courseRepository
                .findAllByCategories(request.getCategories()));
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CreateCourseRequest request) {
        LOGGER.info("createCourse : {}",request);
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
        LOGGER.info("updateCourse : {}",request);
        Course course = courseRepository.findById(request.getId()).get();

        mapper.map(request, course);
        BeanUtils.copyProperties(request, course);

        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCategories(mapper.map(course.getCategories(), typeToken.getType()));
        BeanUtils.copyProperties(course, courseDTO);

        if (!request.getSections().isEmpty() && request.getSections() != null) {
            courseDTO.setSections(this.updateSectionList(request.getSections(), course));
        }
        courseRepository.save(course);
        return courseDTO;
    }

    @Override
    public List<CourseDTO> findAllCourseByStatus(Boolean status) {
        return convertToListCourseDTO(courseRepository.findAllByStatus(status));
    }

    @Override
    public void enrollCourse(EnrollRequest request) {
        Account account = accountRepository.findByEmail(securityService.getCurrentUser().getEmail());
        if(account == null) {
            throw new BizException(ResponseEnum.USERNAME_NOT_EXIST,"Account not found");
        }
        Course course = this.findById(request.getCourseId());
        course.setAccounts(account);
        if(course.checkValidRegister(account)){
            throw new BizException(ResponseEnum.COURSE_ALREADY_IN_ACCOUNT,"Tài khoản đã đăng ký khóa học");
        }
        if(course.getSubscriberNumber()!=null){
            course.setSubscriberNumber(course.getSubscriberNumber()+1);
        } else {
            course.setSubscriberNumber(1L);
        }
        courseRepository.save(course);



    }

    @Override
    public void deleteCourse(Long id) {
        LOGGER.info("deleteCourse : {}",id);
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

    @Override
    public Page<CourseDTO> findCourse(String textSearch, Pageable pageable) {
        return courseRepository.findCourse(textSearch, pageable);
    }

    private List<LessonDTO> saveLessonList(List<CreateLessonRequest> requests, Long sectionId) {
        return lessonService.addLessons(requests, sectionId);
    }
    private List<LessonDTO> updateLessonList(List<UpdateLessonRequest> requests, Long sectionId) {
        return lessonService.updateLessons(requests, sectionId);
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

    private List<SectionDTO> updateSectionList(List<UpdateSectionRequest> requests, Course course) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();

        for (UpdateSectionRequest request : requests) {
            SectionDTO sectionDTO = sectionService.updateSection(request, course);
            if (!request.getLessons().isEmpty() && request.getLessons() != null) {
                sectionDTO.setLessons(this.updateLessonList(request.getLessons(), sectionDTO.getId()));
            }
            sectionDTOList.add(sectionDTO);
        }
        return sectionDTOList;
    }

    private List<Category> getListCategory(List<Long> ids) {
        List<Category> categories = categoryService.getCategoriesInIds(ids);
        for (Category category : categories){
            category.setCourseCount(category.getCourseCount()+1);
        }
        return categoryService.saveAll(categories);
    }

    private List<CourseDTO> convertToListCourseDTO(List<Course> courses) {
        TypeToken<List<CourseDTO>> typeToken = new TypeToken<List<CourseDTO>>() {
        };
        return mapper.map(courses, typeToken.getType());
    }

    private Course findById(Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(!course.isPresent()){
            throw new BizException(ResponseEnum.NOT_FOUND,"Course not found");
        }
        return course.get();
    }
}
