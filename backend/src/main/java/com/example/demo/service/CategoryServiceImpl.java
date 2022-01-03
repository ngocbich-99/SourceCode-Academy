package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.request.category.CreateCategoryRequest;
import com.example.demo.model.request.category.UpdateCategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CourseRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
        List<CategoryDTO> categoryDTOS = mapper.map(listCategory, typeToken.getType());
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
//            throw new NotFoundException("Not found category");
        }
        return this.convertToCategoryDTO(category.get());
    }

    @Override
    public List<Category> getCategoriesInIds(List<Long> id) {
        return this.categoryRepository.findAllById(id);
    }

    @Override
    public CategoryDTO createCategory(CreateCategoryRequest request) {
////        kiem tra name danh muc da ton tai chua
        request.setName(request.getName().trim().toLowerCase(Locale.ROOT));
        Category rs = categoryRepository.findByName(request.getName());
        if (rs != null) {
            throw new InternalException("Category is already in db");
        }
        Category category = new Category();
        BeanUtils.copyProperties(request, category);
        return this.convertToCategoryDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(UpdateCategoryRequest request) {
        Optional<Category> category = categoryRepository.findById(request.getId());
        BeanUtils.copyProperties(request, category.get());
        categoryRepository.save(category.get());
        return this.convertToCategoryDTO(categoryRepository.save(category.get()));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new BizException(ResponseEnum.NOT_FOUND,"Not found Category");
        }
        categoryRepository.deleteById(id);
        categoryRepository.delete(category.get());
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

}
