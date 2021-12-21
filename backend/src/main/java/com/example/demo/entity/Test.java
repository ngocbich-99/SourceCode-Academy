package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

public class Test {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Long courseId;

    private Long createdTime;

    private String name;

    private Integer level;

    private Long timeTest;

    private Integer totalOfQues;

    @ManyToMany
    private Collection<Question> questions;
}
