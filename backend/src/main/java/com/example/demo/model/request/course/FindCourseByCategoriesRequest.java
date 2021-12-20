package com.example.demo.model.request.course;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FindCourseByCategoriesRequest {

    @NotNull
    private List<String> categories;
}
