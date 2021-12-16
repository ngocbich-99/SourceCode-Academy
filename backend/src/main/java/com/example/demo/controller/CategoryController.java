package com.example.demo.controller;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.model.request.CategoryReq;
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
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

//    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryReq categoryReq) {
        Category category = categoryService.createCategory(categoryReq);
        return ResponseEntity.ok(category);
    }

//    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryReq req, @PathVariable int id) {
        Category category = categoryService.updateCategory(req, id);
        return ResponseEntity.ok(category);
    }

//    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category success");
    }
}
