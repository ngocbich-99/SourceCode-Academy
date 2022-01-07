package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.constant.RoleConstant.HOC_VIEN;

@Getter
@Setter
@Entity
@DynamicInsert
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String fullName;

    private String password;

    private String username;

    private String phone;

    private Boolean isActivate = Boolean.TRUE;

    private String role = HOC_VIEN;

    private Long createdTime;

    /**
     * Json Object
     * {@Link List String}
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.LAZY)
    private List<CourseEnroll> courseEnrolls;

    public void addCourseEnrolls(CourseEnroll courseEnroll){
        this.courseEnrolls.add(courseEnroll);
    }
    public List<CourseEnroll>  getCourseEnrolls(){
        return this.courseEnrolls;
    }

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "accounts")
    private List<Course> courses;


    public void setCourse(Course course) {
        this.courses.add(course);
    }


    @Transient
    public List<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }


}
