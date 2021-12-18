package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {

    public List<Section> findAllByCourse(Course course);

}
