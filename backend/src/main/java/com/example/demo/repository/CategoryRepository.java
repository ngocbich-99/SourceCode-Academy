package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String nameCategory);

    Category existsByName(String nameCategory);

    @Transactional
    @Modifying
    @Query(value = "Delete from course_category where category_id = :categoriesId",nativeQuery = true)
    void deleteById(@Param("categoriesId") Long id);

}
