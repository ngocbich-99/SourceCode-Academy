package com.example.demo.controller;

import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.request.category.CreateCategoryRequest;
import com.example.demo.model.request.category.UpdateCategoryRequest;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.constant.CommonConstant.SUCCESS;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/category")
public class CategoryController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public CloudResponse<List<CategoryDTO>> getList() {
        return CloudResponse.ok(categoryService.getListCategory());
    }

    @GetMapping("/{id}")
    public CloudResponse<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return CloudResponse.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryRequest body) {
        return ResponseEntity.ok(categoryService.createCategory(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> editCategory(@Valid @RequestBody UpdateCategoryRequest body) {
        return ResponseEntity.ok(categoryService.updateCategory(body));
    }

    @DeleteMapping("/{id}")
    public CloudResponse<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return CloudResponse.ok(SUCCESS,"Delete category success");
    }
}
