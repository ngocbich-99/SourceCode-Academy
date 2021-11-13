package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity

public class Category {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idCategory;

    private Date createdTime;

    private String nameCategory;

    @OneToMany
    private Set<Course> courseSet;
}
