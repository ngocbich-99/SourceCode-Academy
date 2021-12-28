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
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @ApiOperation(value = "Api get all khóa học private ")
    @GetMapping("/list")
    public CloudResponse<List<CourseDTO>> getList() {
        return CloudResponse.ok(courseService.getAll());
    }

    @ApiOperation(value = "Api get all khóa học private ")
    @GetMapping("/private")
    public CloudResponse<List<CourseDTO>> getPrivateCourse() {
        return CloudResponse.ok(courseService.findAllCourseByStatus(CommonConstant.PRIVATE_COURSE));
    }

    @ApiOperation(value = "Api get all khóa học public ")
    @GetMapping("/public")
    public CloudResponse<List<CourseDTO>> getPublicCourse() {
        return CloudResponse.ok(courseService.findAllCourseByStatus(CommonConstant.PUBLIC_COURSE));
    }


    @ApiOperation(value = "Api get khóa học bằng id")
    @GetMapping("/{id}")
    public CloudResponse<CourseDTO> getCourseById(@PathVariable Long id) {
        LOGGER.info("GET /api/course/{id} - > {}", id);
        CourseDTO course = courseService.getCourseDTOById(id);
        return CloudResponse.ok(course);
    }


    @ApiOperation(value = "Api tạo khóa học")
    @PostMapping
    public CloudResponse<CourseDTO> createCourse(@Valid @RequestBody CreateCourseRequest courseReq) {
        LOGGER.info("POST /api/course- > {}", courseReq);
        return CloudResponse.ok(courseService.createCourse(courseReq));
    }

    @ApiOperation(value = "Api update khóa học")
    @PutMapping
    public CloudResponse<CourseDTO> editCourse(@Valid @RequestBody UpdateCourseRequest body) {
        return CloudResponse.ok(courseService.updateCourse(body));
    }

    @ApiOperation(value = "Api xóa khóa học bằng id")
    @DeleteMapping("/delete/{id}")
    public CloudResponse<String> deleteCourse(@PathVariable Long id) {
        LOGGER.info("DEL /api/course/delete/{id} - > {}", id);
        courseService.deleteCourse(id);
        return CloudResponse.ok(SUCCESS, "Delete category success");
    }

    @ApiOperation(value = "Api get khóa học với categories name ")
    @PostMapping("/categories")
    public CloudResponse<List<CourseDTO>> findCourseByCategories(@Valid @RequestBody FindCourseByCategoriesRequest body) {
        LOGGER.info("POST /api/course/categories - > {}", body);
        return CloudResponse.ok(courseService.findAllByCategoriesName(body));
    }

    @ApiOperation(value = "Api đăng ký khóa học")
    @PostMapping("/enroll")
    public CloudResponse<String> enroll(@Valid @RequestBody EnrollRequest body) {
        LOGGER.info("POST /api/course/enroll - > {}", body);
        courseService.enrollCourse(body);
        return CloudResponse.ok(SUCCESS, "Enroll course success");
    }

    @ApiOperation(value = "Api get khóa học phân trang")
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


    @ApiOperation(value = "Api get khóa của user đang đăng nhập")
    @GetMapping("/current-user")
    public CloudResponse<PageData<CourseDTO>> getCourseOfCurrentUser(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String textSearch,
            @RequestParam(required = false, defaultValue = "name") String sortProperty,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        LOGGER.info("GET /api/course/current-user");
        Pageable pageable = PageRequest
                .of(page, pageSize, Sort.Direction.fromString(sortOrder), sortProperty);
        return CloudResponse.ok(courseService.findCourseOfCurrentUser(textSearch.trim(),pageable));
    }


}
