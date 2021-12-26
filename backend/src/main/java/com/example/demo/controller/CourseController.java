package com.example.demo.controller;

import com.example.demo.common.PageData;
import com.example.demo.constant.CommonConstant;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.course.CreateCourseRequest;
import com.example.demo.model.request.course.EnrollRequest;
import com.example.demo.model.request.course.FindCourseByCategoriesRequest;
import com.example.demo.model.request.course.UpdateCourseRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.constant.CommonConstant.SUCCESS;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);


    @GetMapping("/list")
    public CloudResponse<List<CourseDTO>> getList() {
        return CloudResponse.ok(courseService.getAll());
    }

    @GetMapping("/private")
    public CloudResponse<List<CourseDTO>> getPrivateCourse() {
        return CloudResponse.ok(courseService.findAllCourseByStatus(CommonConstant.PRIVATE_COURSE));
    }

    @GetMapping("/public")
    public CloudResponse<List<CourseDTO>> getPublicCourse() {
        return CloudResponse.ok(courseService.findAllCourseByStatus(CommonConstant.PUBLIC_COURSE));
    }


    @GetMapping("/{id}")
    public CloudResponse<CourseDTO> getCourseById(@PathVariable Long id) {
        LOGGER.info("GET /api/course/{id} - > {}", id);
        CourseDTO course = courseService.getCourseDTOById(id);
        return CloudResponse.ok(course);
    }


    @PostMapping
    public CloudResponse<CourseDTO> createCourse(@Valid @RequestBody CreateCourseRequest courseReq) {
        LOGGER.info("POST /api/course- > {}", courseReq);
        return CloudResponse.ok(courseService.createCourse(courseReq));
    }

    @PutMapping
    public CloudResponse<CourseDTO> editCourse(@Valid @RequestBody UpdateCourseRequest body) {
        return CloudResponse.ok(courseService.updateCourse(body));
    }

    @DeleteMapping("/delete/{id}")
    public CloudResponse<String> deleteCourse(@PathVariable Long id) {
        LOGGER.info("DEL /api/course/delete/{id} - > {}", id);
        courseService.deleteCourse(id);
        return CloudResponse.ok(SUCCESS, "Delete category success");
    }

    @PostMapping("/categories")
    public CloudResponse<List<CourseDTO>> findCourseByCategories(@Valid @RequestBody FindCourseByCategoriesRequest body) {
        LOGGER.info("POST /api/course/categories - > {}", body);
        return CloudResponse.ok(courseService.findAllByCategoriesName(body));
    }

    @PostMapping("/enroll")
    public CloudResponse<String> enroll(@Valid @RequestBody EnrollRequest body) {
        LOGGER.info("POST /api/course/enroll - > {}", body);
        courseService.enrollCourse(body);
        return CloudResponse.ok(SUCCESS, "Enroll course success");
    }

    @GetMapping
    public CloudResponse<PageData<CourseDTO>> getCourse(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam(required = false, defaultValue = "name") String sortProperty,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        Pageable pageable = PageRequest
                .of(page, pageSize, Sort.Direction.fromString(sortOrder), sortProperty);

        return CloudResponse.ok(courseService.findCourse(textSearch.trim(), pageable));
    }


}
