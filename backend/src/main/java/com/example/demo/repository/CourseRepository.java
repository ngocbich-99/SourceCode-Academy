package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


//    @Query(name = "SELECT u FROM Course u WHERE u.status = true")
    public List<Course> findAll();

    //SELECT cs FROM Course cs LEFT JOIN cs. c WHERE cs.id = a.user
    @Query("SELECT c FROM Course c LEFT OUTER JOIN c.categories g WHERE g.name in :categoriesName")
    public List<Course> findAllByCategories(@Param("categoriesName") List<String> categories);
}
