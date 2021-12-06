package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.model.request.CategoryReq;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        List<Category> listCategory = categoryService.getListCategory();
        return ResponseEntity.ok(listCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryReq categoryReq) {
        Category category = categoryService.createCategory(categoryReq);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryReq req, @PathVariable int id) {
        Category category = categoryService.updateCategory(req, id);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category success");
    }
}
