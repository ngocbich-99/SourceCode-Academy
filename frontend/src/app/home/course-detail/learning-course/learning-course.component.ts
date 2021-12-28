import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { Course } from '../../courses/course.model';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { AccountService } from 'src/app/services/account.service';
import { Account } from '../../accounts/account.model';

@Component({
  selector: 'app-learning-course',
  templateUrl: './learning-course.component.html',
  styleUrls: ['./learning-course.component.css']
})
export class LearningCourseComponent implements OnInit {
  courseSelected: Course = {};
  isCollapsed = false;
  teacher: Account = {};

  constructor(
    private _location: Location,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private accountService: AccountService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.getCourseById(params['id']);
    })
  }

  getCourseById(id: number) {
    this.courseService.getCourse(id).subscribe(resData => {
      this.courseSelected = resData;
      this.getTeacher(this.courseSelected.teacherId);
    })
  }
  getTeacher(id?: number) {
    this.accountService.getAccountById(id).subscribe(resData => {
      this.teacher = resData;
    })
  }

  getTotalLesson() {
    return this.courseSelected.sections?.reduce((accumulator, sectionCurrent) => {
      return accumulator + sectionCurrent?.lessons?.length;
    }, 0)
  }

  backPrevious() {
    // this.router.navigate([".."]);
    this._location.back();
  }

}
