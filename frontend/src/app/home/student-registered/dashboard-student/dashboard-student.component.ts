import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { CourseService } from 'src/app/services/course.service';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-dashboard-student',
  templateUrl: './dashboard-student.component.html',
  styleUrls: ['./dashboard-student.component.css']
})
export class DashboardStudentComponent implements OnInit {
  listCourseNew: Course[] = [];

  constructor(
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.getListCourseNew();
  }

  getListCourseNew() {
    this.courseService.getCoursesPublic().subscribe(resData => {
      if (!!resData.data) {
        this.listCourseNew = resData.data;
      }
    })
  }

}
