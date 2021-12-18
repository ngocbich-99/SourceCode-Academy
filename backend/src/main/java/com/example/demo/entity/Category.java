package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int idCategory;

    private long createdTime;

    @NotNull
    private String nameCategory;

    @Size(min = 3, max = 4000)
    @NotNull
    private String description;

//    @OneToMany
//    private Set<Course> courseSet;

//    @Fetch(FetchMode.JOIN) fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true
    @ManyToMany(mappedBy = "category")
    private List<Course> courses;
}
