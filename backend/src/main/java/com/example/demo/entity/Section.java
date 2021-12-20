package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="section")
public class Section {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private long createdTime;

    private String name;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "section")
    private List<Lesson> lessons;

}
