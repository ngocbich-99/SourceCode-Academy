package com.example.demo.service;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.model.request.category.CreateCategoryRequest;
import com.example.demo.model.request.category.UpdateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
     List<CategoryDTO> getListCategory();

     CategoryDTO getCategoryById(Long id);

     List<Category> getCategoriesInIds(List<Long> id);

     CategoryDTO createCategory(CreateCategoryRequest request);

     CategoryDTO updateCategory(UpdateCategoryRequest request);

     void deleteCategory(Long id);

    List<Category> saveAll(List<Category> categories);
}
