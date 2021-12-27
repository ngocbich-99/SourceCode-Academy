import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/home/courses/course.model';

@Component({
  selector: 'app-course-item-student',
  templateUrl: './course-item-student.component.html',
  styleUrls: ['./course-item-student.component.css']
})
export class CourseItemStudentComponent implements OnInit {
  @Input() courses: Course[] = [];

  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  routerCourseDetail(id?: number) {
    this.router.navigate(['home', 'course-detail', id]);
  }

}
