package com.example.demo.controller;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.model.request.category.CreateCategoryRequest;
import com.example.demo.model.request.category.UpdateCategoryRequest;
import com.example.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> getList() {
        return ResponseEntity.ok(categoryService.getListCategory());
    }

//    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

//    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CreateCategoryRequest body) {
        Category category = categoryService.createCategory(body);
        return ResponseEntity.ok(category);
    }

//    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@Valid @RequestBody UpdateCategoryRequest body, @PathVariable int id) {
        Category category = categoryService.updateCategory(body);
        return ResponseEntity.ok(category);
    }

//    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category success");
    }
}
