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
  @Input() isCourseStudent: boolean | undefined;
  
  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  ngOnChanges() {
    console.log('course item ', this.courses);
  }

  routerCourseDetail(id?: number) {
    this.router.navigate(['home', 'course-detail', id]);
  }

}
