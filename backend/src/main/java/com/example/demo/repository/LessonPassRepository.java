package com.example.demo.repository;

import com.example.demo.entity.LessonPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonPassRepository extends JpaRepository<LessonPass,Long> {
}
