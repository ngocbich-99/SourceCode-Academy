package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReq {

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String nameCategory;

    @NotNull(message = "Discription is required")
    @NotEmpty(message = "Discription is required")
    private String discription;

    private long createdTime;
}
