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
    private int idSection;

    @ManyToOne
    private Course course;

    private long createdTime;

    private String nameSection;

    @OneToMany(mappedBy = "section")
    private List<Lesson> listLesson;

}
