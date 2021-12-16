package com.example.demo.service;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.mapper.CategoryMapper;
import com.example.demo.model.request.CategoryReq;
import com.example.demo.repository.CategoryRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ModelMapper mapper;
    @Override
    public List<CategoryDTO> getListCategory() {
        List<Category> listCategory = categoryRepository.findAll();
        TypeToken<List<CategoryDTO>> typeToken = new TypeToken<List<CategoryDTO>>() {
        };
        List<CategoryDTO> categoryDTOS = mapper.map(listCategory,typeToken.getType());
        return categoryDTOS;
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new NotFoundException("Not found category");
        }
        return category.get();
    }

    @Override
    public Category createCategory(CategoryReq categoryReq) {
//        kiem tra name danh muc da ton tai chua
        Category rs = categoryRepository.findBynameCategory(categoryReq.getNameCategory());
        if (rs != null) {
            throw new InternalException("Category is already in db");
        }
//        convert Request to Entity
        Category category = new Category();
        category = CategoryMapper.reqToCategory(categoryReq);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(CategoryReq categoryReq, int id) {
//        kiem tra voi cac danh muc khac xem danh muc da ton tai chua
//        Category rs = categoryRepository.findBynameCategory((categoryReq.getNameCategory()));
//        if (rs != null) {
//            throw new InternalException("Category is already in db");
//        }

//        get category in db
        Optional<Category> categoryInDb = categoryRepository.findById(id);
        Category category = categoryInDb.get();

//        update category
        category.setNameCategory(categoryReq.getNameCategory());
        category.setDescription(categoryReq.getDescription());
        category.setCreatedTime(categoryReq.getCreatedTime());

        try {
            categoryRepository.save(category);
        } catch (Exception ex) {
            throw new InternalException("Db error. Can't update category");
        }
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new NotFoundException("Not found category");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalException("Db error. Can't delete category");
        }
    }
}
