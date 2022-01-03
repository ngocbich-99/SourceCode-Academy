package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    //    @Query(name = "SELECT u FROM Course u WHERE u.status = true")
    public List<Course> findAll();

    @Query(value = "delete from course_category where id = ?1", nativeQuery = true)
    public void deleteIntermediateByCategoryId(@Param("categoriesId") Long id);

    //SELECT cs FROM Course cs LEFT JOIN cs. c WHERE cs.id = a.user
    @Query("SELECT c FROM Course c LEFT OUTER JOIN c.categories g WHERE g.name in :categoriesName")
    public List<Course> findAllByCategories(@Param("categoriesName") List<String> categories);

    public List<Course> findAllByStatus(Boolean status);

    @Query(value = "insert into course_students(course_id,students_id) values(?1,?2)", nativeQuery = true)
    public void enrollCourse(Long studentId, Long courseId);

    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:searchText, '%'))")
    Page<Course> findCourse(@Param("searchText") String searchText,
                            Pageable pageable);

    @Query("SELECT c FROM Course c LEFT OUTER JOIN c.accounts g WHERE g.id = :accountId and LOWER(c.name) LIKE LOWER(CONCAT('%',:searchText, '%'))")
    Page<Course> findCourseOfCurrentUser(@Param("searchText") String searchText,
                                         @Param("accountId") Long accountId,
                                         Pageable pageable);


    @Query("SELECT c FROM Course c WHERE c.id in :courseIds and LOWER(c.name) LIKE LOWER(CONCAT('%',:searchText, '%'))")
    Page<Course> findCoursePassedInIds(@Param("searchText") String searchText,
                                         @Param("courseIds") List<Long> courseIds,
                                         Pageable pageable);
}
