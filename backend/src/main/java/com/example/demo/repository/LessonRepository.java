package com.example.demo.repository;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

    public List<Lesson> findAllBySection(Section section);
}
