import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-item-student',
  templateUrl: './course-item-student.component.html',
  styleUrls: ['./course-item-student.component.css']
})
export class CourseItemStudentComponent implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  routerCourseDetail() {
    this.router.navigate(['home', 'course-detail']);
  }

}
