package com.example.demo.service;

import com.example.demo.common.PageData;
import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseEnroll;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import com.example.demo.jwt.SecurityService;
import com.example.demo.model.dto.*;
import com.example.demo.model.request.course.*;
import com.example.demo.model.request.lesson.CreateLessonRequest;
import com.example.demo.model.request.lesson.UpdateLessonRequest;
import com.example.demo.model.request.section.CreateSectionRequest;
import com.example.demo.model.request.section.UpdateSectionRequest;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CourseEnrollRepository;
import com.example.demo.repository.CourseRepository;
import com.google.gson.Gson;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseEnrollRepository courseEnrollRepository;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Gson gson;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private FileService fileService;

    private Type listCourseDTOType;

    @Override
    public List<CourseDTO> getAll() {
        List<Course> all = courseRepository.findAll();
        TypeToken<List<CourseDTO>> typeToken = new TypeToken<List<CourseDTO>>() {
        };
        LOGGER.info("Type {}", CourseDTO.class);
        LOGGER.info("typeToken.getType() : {} , typeToken{}", typeToken.getType(), typeToken.getType());
        return mapper.map(all, typeToken.getType());
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }


    @Override
    public CourseDTO getCourseDTOById(Long id) {
        LOGGER.info("getCourseDTOById : {}", id);
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
        LOGGER.info("findAllByCategoriesName : {}", request);
        return this.convertToListCourseDTO(courseRepository
                .findAllByCategories(request.getCategories()));
    }

    @Override
    public List<CourseDTO> findAllByCategoriesNameAndStatus(FindCourseByCategoriesRequest request,Boolean status) {
        LOGGER.info("findAllByCategoriesNameAndStatus : {}", request);
        return this.convertToListCourseDTO(courseRepository
                .findAllByCategoriesAndStatus(request.getCategories(),status));
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CreateCourseRequest request) {
        LOGGER.info("createCourse : {}", request);
        Course course = new Course();
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            course.setCategories(this.getListCategory(request.getCategoryIds()));
        }
        BeanUtils.copyProperties(request, course);
        course.setTeacherId(getCurrentAccount().getId());
//        course.setImgCover(fileService.save(request.getImg()));
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
        LOGGER.info("updateCourse : {}", request);
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
        if (account == null) {
            throw new BizException(ResponseEnum.USERNAME_NOT_EXIST, "Account not found");
        }
        Course course = this.findById(request.getCourseId());
        if (course.checkValidRegister(account)) {
            throw new BizException(ResponseEnum.COURSE_ALREADY_IN_ACCOUNT, "Tài khoản đã đăng ký khóa học");
        }
        if (course.getSubscriberNumber() != null) {
            course.setSubscriberNumber(course.getSubscriberNumber() + 1);
        } else {
            course.setSubscriberNumber(1L);
        }
        course.addAccounts(account);
        CourseEnroll courseEnroll = new CourseEnroll();
        courseEnroll.setAccount(account);
        courseEnroll.setCoursePassed(false);
        courseEnroll.setLessonPassed("[]");
        courseEnroll.setCourseId(request.getCourseId());
//        courseRepository.save(course);
        courseEnrollRepository.save(courseEnroll);

    }

    @Override
    public void markCoursePass(MarkCoursePassRequest request) {
        LOGGER.info("markCoursePass : {}", request);
        CourseEnroll courseEnroll = this.getCourseEnroll(this.getCurrentAccount().getId(), request.getCourseId());
        courseEnroll.setCoursePassed(true);
        courseEnrollRepository.save(courseEnroll);
    }

    private CourseEnroll getCourseEnroll(Long accountId,Long courseId){
        return courseEnrollRepository.findByAccountIdAndCourseId(accountId,courseId);
    }

    @Override
    public void deleteCourse(Long id) {
        LOGGER.info("deleteCourse : {}", id);
            Optional<Course> course = courseRepository.findById(id);
            course.get().setCategories(new ArrayList<>());
            courseRepository.save(course.get());
            if (course.isPresent()) {
                if(course.get().getAccount().size() > 0){
                    throw new BizException(ResponseEnum.PERMISSIONS_DENY,"Khóa học đã có học viên, không thể xóa");
                }
                sectionService.deleteByCourse(course.get());
                courseRepository.delete(course.get());
            }
    }

    @Override
    public PageData<CourseDTO> findCourse(String textSearch, Pageable pageable) {
        return this.convertToPageCourseDTO(courseRepository.findCourse(textSearch, pageable), pageable);
    }

    @Override
    public PageData<CourseDTO> findCourseOfCurrentUser(String textSearch, Pageable pageable) {
        Page<Course> courseOfCurrentUser = courseRepository.findCourseOfCurrentUser(textSearch,
                securityService.getCurrentUser().getId(),
                pageable);
        Account account = accountRepository.findById(securityService.getCurrentUser().getId()).get();
        List<CourseEnrollDTO> dt = this.convertX(account.getId());
        PageData<CourseDTO> courseDTOPageData = this.convertToPageCourseDTO(courseOfCurrentUser
                , pageable);
        for(CourseDTO c : courseDTOPageData.getContents()){

            for(CourseEnrollDTO ce : dt){
                if(c.getId().equals(ce.getCourseId())){
                    c.setPassed(ce.getLessonPassed().size());
                }
            }
        }
        return courseDTOPageData;

    }

    @Override
    public PageData<CourseEnrollDTO> findCoursePassedOfCurrentUser(String textSearch, Pageable pageable) {
//        List<Long> coursePassedId = this.getListCoursePassed();
//        securityService.getCurrentUser().getCoursePass()
//        return this.convertToPageCourseDTO(
//                courseRepository.findCoursePassedInIds(textSearch,
//                        coursePassedId,
//                        pageable), pageable);
        return new PageData<CourseEnrollDTO>(
                this.convert(securityService.getCurrentUser().getId()),
                0,
                0,
                false
        );

    }
    public List<CourseEnrollDTO> convert(Long accountId){
        List<CourseEnrollDTO> courseEnrollDTOs = new ArrayList<>();
        TypeToken<List<Long>> typeToken = new TypeToken<List<Long>>(){};
        List<CourseEnroll> courseEnrolls = courseEnrollRepository.findByAccountId(accountId);
        for(CourseEnroll c : courseEnrolls){
           if(c.isCoursePassed()){
               CourseEnrollDTO courseEnrollDTO = new CourseEnrollDTO();
               mapper.map(c,courseEnrollDTO);
               courseEnrollDTO.setLessonPassed(gson.fromJson(c.getLessonPassed(),typeToken.getType()));
               courseEnrollDTOs.add(courseEnrollDTO);
           }
        }
        return courseEnrollDTOs;
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
        for (Category category : categories) {
            category.setCourseCount(category.getCourseCount() + 1);
        }
        return categoryService.saveAll(categories);
    }

    private List<CourseDTO> convertToListCourseDTO(List<Course> courses) {
        TypeToken<List<CourseDTO>> typeToken = new TypeToken<List<CourseDTO>>() {
        };

        return mapper.map(courses, typeToken.getType());
    }

    public List<CourseEnrollDTO> convertX(Long accountId){
        List<CourseEnrollDTO> courseEnrollDTOs = new ArrayList<>();
        TypeToken<List<Long>> typeToken = new TypeToken<List<Long>>(){};
        List<CourseEnroll> courseEnrolls = courseEnrollRepository.findByAccountId(accountId);
        for(CourseEnroll c : courseEnrolls){
                CourseEnrollDTO courseEnrollDTO = new CourseEnrollDTO();
                mapper.map(c,courseEnrollDTO);
                courseEnrollDTO.setLessonPassed(gson.fromJson(c.getLessonPassed(),typeToken.getType()));
                courseEnrollDTOs.add(courseEnrollDTO);
        }
        return courseEnrollDTOs;
    }

    private Course findById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            throw new BizException(ResponseEnum.NOT_FOUND, "Course not found");
        }
        return course.get();
    }

    private PageData<CourseDTO> convertToPageCourseDTO(Page<Course> page, Pageable pageable) {
        return new PageData<>(
                this.convertToListCourseDTO(page.getContent()),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext()
        );
    }

    private Account getCurrentAccount() {
        Optional<Account> account = accountRepository.findById(securityService.getCurrentUser().getId());
        if (account.isPresent()) {
            return account.get();
        }
        throw new BizException(ResponseEnum.USERNAME_NOT_EXIST, "Username not exist");
    }

    private List<Long> getListCoursePassed() {
        TypeToken<List<Long>> typeToken = new TypeToken<List<Long>>() {
        };
        return gson.fromJson(securityService.getCurrentUser().getCoursePass(), typeToken.getType());
    }
}
