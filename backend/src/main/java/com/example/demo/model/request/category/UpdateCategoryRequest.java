package com.example.demo.model.request.category;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryRequest {

    @NotNull(message = "Id is required")
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    private String description;

}
