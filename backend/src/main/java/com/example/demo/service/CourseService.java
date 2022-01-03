package com.example.demo.service;


import com.example.demo.common.PageData;
import com.example.demo.entity.Course;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.request.course.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAll();

    Course getCourseById(Long id);

    CourseDTO getCourseDTOById(Long id);

    List<CourseDTO> findAllByCategoriesName(FindCourseByCategoriesRequest request);

    CourseDTO createCourse(CreateCourseRequest request);

    CourseDTO updateCourse(UpdateCourseRequest request);

    List<CourseDTO> findAllCourseByStatus(Boolean status);

    void enrollCourse(EnrollRequest request);

    void markCoursePass(MarkCoursePassRequest request);

    void deleteCourse(Long id);

    PageData<CourseDTO> findCourse(String textSearch, Pageable pageable);

    PageData<CourseDTO> findCourseOfCurrentUser(String textSearch,Pageable pageable);

    PageData<CourseDTO> findCoursePassedOfCurrentUser(String textSearch,Pageable pageable);
}
