package com.example.demo.repository;

import com.example.demo.entity.CourseEnroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseEnrollRepository extends JpaRepository<CourseEnroll,Long> {

    @Query(value = "select c from CourseEnroll c INNER JOIN c.account a where a.id = :accountId and c.courseId = :courseId")
    CourseEnroll findByAccountIdAndCourseId(@Param("accountId")Long accountId,@Param("courseId")Long courseId);

    @Query(value = "select c from CourseEnroll c INNER JOIN c.account a where a.id = :accountId")
    List<CourseEnroll> findByAccountId(@Param("accountId")Long accountId);
}
