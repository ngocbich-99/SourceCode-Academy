package com.example.demo.service;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.model.request.CategoryReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryDTO> getListCategory();

    public Category getCategoryById(int id);

    public Category createCategory(CategoryReq categoryReq);

    public Category updateCategory(CategoryReq categoryReq, int id);

    public void deleteCategory(int id);


}
