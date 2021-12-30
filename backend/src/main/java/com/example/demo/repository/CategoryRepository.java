package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findByName(String nameCategory);
    public Category existsByName(String nameCategory);

    @Query(value = "delete from course_category where category_id = ?1", nativeQuery = true)
    public boolean deleteIntermediateByCategoryId(@Param("categoriesId") Long id);

}
