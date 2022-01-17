import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course.service';
import { Course, CoursePass } from '../../courses/course.model';

@Component({
  selector: 'app-courses-student',
  templateUrl: './courses-student.component.html',
  styleUrls: ['./courses-student.component.css']
})
export class CoursesStudentComponent implements OnInit {
  listCourseLearning: Course[] = [];
  listCourseLearned: Course[] = [];
  listCoursePass: CoursePass[] = [];

  pageSize: number = 10;
  page: number = 0;
  
  constructor(
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.getCoursesLearning(this.pageSize, this.page, '', 'createdTime', 'desc');
    this.getCoursesLearned(this.pageSize, this.page, '', 'createdTime', 'desc');
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
      this.listCourseLearning.forEach(course => {
        course.isCompleted = false;
      })
    })
  }

  getCoursesLearned(
    pageSize: number, 
    page: number, 
    textSearch: string, 
    sortProperty: string, 
    sortOrder: string
  ) {
    this.courseService.getCoursesLearned(pageSize, page, textSearch, sortProperty, sortOrder).subscribe(resData => {
      console.log('courses learned', resData);
      if (this.listCoursePass.length === 0) {
        this.listCoursePass = resData.data.contents;
      } else {
        this.listCoursePass = this.listCoursePass.concat(resData.data.contents);
      }
      this.listCoursePass.forEach(course => {
        this.courseService.getCourse(course.courseId).subscribe(resData => {
          this.listCourseLearned.push(resData);
        })
      })
      this.listCourseLearned.forEach(course => {
        course.isCompleted = true;
      })
    })
  }

}
