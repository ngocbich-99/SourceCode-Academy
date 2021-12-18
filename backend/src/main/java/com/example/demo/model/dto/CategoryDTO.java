package com.example.demo.model.dto;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private int idCategory;

    private long createdTime;

    private String nameCategory;

    private String description;

}