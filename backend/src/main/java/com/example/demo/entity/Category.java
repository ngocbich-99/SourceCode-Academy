package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idCategory;

    private Date createdTime;

    private String nameCategory;

    private String description;

//    @OneToMany
//    private Set<Course> courseSet;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}
