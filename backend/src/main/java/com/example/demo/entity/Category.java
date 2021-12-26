package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Size(min = 3, max = 4000)
    @NotNull
    private String description;

    private Long courseCount;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Course> courses;
}
