import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/home/courses/course.model';
import { CourseService } from 'src/app/services/course.service';

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
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
  }

  ngOnChanges() {
    console.log('course item ', this.courses);
  }

  // async getTotalLesson(course: Course) {
  //   const dataCourse: Course = await this.courseService.getCourse(course.id).toPromise();
  //   return dataCourse.sections?.reduce((accumulator, sectionCurrent) => {
  //     return accumulator + sectionCurrent?.lessons?.length;
  //   }, 0)
  // }

  routerCourseDetail(id?: number) {
    this.router.navigate(['home', 'course-detail', id]);
  }

}
