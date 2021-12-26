package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long teacherId;


    @ManyToMany
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @NotNull
    private String name;

    private Long createdTime;

    @NotNull
    private Boolean status;

    @NotNull
    private int level;

    @NotNull
    private String imgCover;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Account> students;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @Getter(AccessLevel.NONE)
    private List<Section> sections;

    @Column(name="subscriber_number", columnDefinition = "bigint default '0'")
    private Long subscriberNumber;


}
