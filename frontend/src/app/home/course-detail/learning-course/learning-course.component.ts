import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { Course, Lesson } from '../../courses/course.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { AccountService } from 'src/app/services/account.service';
import { Account } from '../../accounts/account.model';
import { LearningCourseService } from 'src/app/services/learning-course.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-learning-course',
  templateUrl: './learning-course.component.html',
  styleUrls: ['./learning-course.component.css']
})
export class LearningCourseComponent implements OnInit {
  courseSelected: Course = {};
  isCollapsed = false;
  teacher: Account = {};
  urldemo: SafeResourceUrl = '';

  constructor(
    private _location: Location,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private accountService: AccountService,
    private learningService: LearningCourseService,
    private sanitizer: DomSanitizer,
    private router: Router,
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

      if (!!this.courseSelected) {
        this.courseSelected.sections?.forEach(section => {
          section.lessons.forEach(lesson => {
            lesson.urlSafeResourse = this.sanitizer.bypassSecurityTrustResourceUrl(lesson.urlVideo as string);
          })
        })
      }
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

  markLessonPass(lesson?: Lesson) {
    this.learningService.markLessonLearned(lesson?.id).subscribe(resData => {
      console.log('mark lesson learned', resData);
      
    })
  }

  backPrevious() {
    // this.router.navigate(['/home/course-detail', this.courseSelected.id]);
    this._location.back();
  }

}
