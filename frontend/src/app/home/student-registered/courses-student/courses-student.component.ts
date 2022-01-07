import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course.service';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-courses-student',
  templateUrl: './courses-student.component.html',
  styleUrls: ['./courses-student.component.css']
})
export class CoursesStudentComponent implements OnInit {
  listCourseLearning: Course[] = [];

  pageSize: number = 10;
  page: number = 0;
  
  constructor(
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.getCoursesLearning(this.pageSize, this.page, '', 'createdTime', 'desc');
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
