package com.example.demo.service;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.model.request.category.CreateCategoryRequest;
import com.example.demo.model.request.category.UpdateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryDTO> getListCategory();

    public Category getCategoryById(Long id);

    public List<Category> getCategoriesInIds(List<Long> id);

    public Category createCategory(CreateCategoryRequest request);

    public Category updateCategory(UpdateCategoryRequest request);

    public void deleteCategory(Long id);


}
