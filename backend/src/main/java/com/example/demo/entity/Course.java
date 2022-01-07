package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
@DynamicInsert
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long teacherId;


    @ManyToMany
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Category> categories;

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

    @Getter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Account> accounts;

    public List<Account> getAccount() {
        return accounts;
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }

    public Boolean checkValidRegister(Account account) {
        return this.accounts.contains(account);
    }

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @Getter(AccessLevel.NONE)
    private List<Section> sections;

    @Column(name = "subscriber_number")
    private Long subscriberNumber = 0L;


}
