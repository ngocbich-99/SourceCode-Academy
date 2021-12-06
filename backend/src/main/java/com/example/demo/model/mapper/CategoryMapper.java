package com.example.demo.model.mapper;

import com.example.demo.entity.Category;
import com.example.demo.model.request.CategoryReq;

public class CategoryMapper {
    public static Category reqToCategory(CategoryReq categoryReq) {
      Category category = new Category();
      category.setNameCategory(categoryReq.getNameCategory());
      category.setDescription(categoryReq.getDiscription());
      category.setCreatedTime(categoryReq.getCreatedTime());

      return category;
    }
}
