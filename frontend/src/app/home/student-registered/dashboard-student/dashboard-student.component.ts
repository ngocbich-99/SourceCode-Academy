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
  listCourseLearning: Course[] = [];

  pageSize: number = 10;
  page: number = 0;

  constructor(
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.getListCourseNew();
    this.getCoursesLearning(this.pageSize, this.page, '', 'createdTime', 'desc');
  }

  getListCourseNew() {
    this.courseService.getCoursesPublic().subscribe(resData => {
      if (!!resData.data) {
        this.listCourseNew = resData.data;
      }
    })
  }

  getCoursesLearning(
    pageSize: number, 
    page: number, 
    textSearch: string, 
    sortProperty: string, 
    sortOrder: string){
    this.courseService.getCoursesLearning(pageSize, page, textSearch, sortProperty, sortOrder).subscribe(resData => {
      if (this.listCourseLearning.length === 0) {
        this.listCourseLearning  = resData.data.contents;
      } else {
        this.listCourseLearning = this.listCourseLearning.concat(resData.data.contents);
      }
    })
  }

}
