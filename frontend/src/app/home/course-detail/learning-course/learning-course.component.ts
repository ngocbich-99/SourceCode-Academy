import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { Course, Lesson } from '../../courses/course.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { AccountService } from 'src/app/services/account.service';
import { Account } from '../../accounts/account.model';
import { LearningCourseService } from 'src/app/services/learning-course.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { CourseEnroll } from 'src/app/auth/auth.model';
import { UserInfo } from 'src/app/auth/user.model';

@Component({
  selector: 'app-learning-course',
  templateUrl: './learning-course.component.html',
  styleUrls: ['./learning-course.component.css']
})
export class LearningCourseComponent implements OnInit {
  courseSelected: Course = {};
  courseEntrolls: CourseEnroll[] = [];
  
  isCollapsed = false;
  teacher: Account = {};
  urldemo: SafeResourceUrl = '';
  lessonSelected?: Lesson = {};

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
    this.getCourseEntroll();

    // an sidebar
    const drawerContent = document.getElementById('drawer-content');
    drawerContent?.classList.add('margin-zero'); 

    const drawer = document.getElementById('drawer');
    drawer?.classList.add('hidden-sidebar');
  }

  ngOnDestroy() {
    const drawerContent = document.getElementById('drawer-content');
    drawerContent?.classList.remove('margin-zero'); 

    const drawer = document.getElementById('drawer');
    drawer?.classList.remove('hidden-sidebar');
  }

  getCourseEntroll() {
    const data = JSON.parse(localStorage.getItem('userInfo') as string);
    this.courseEntrolls = data.courseEnrolls;
    console.log('data local storage', this.courseEntrolls);
  }

  checkLessonPass(lesson: Lesson): boolean {
    let mark = false;
    this.courseEntrolls.forEach(courseEnroll => {
      if (courseEnroll.courseId === this.courseSelected.id) {
        // courseEnroll.idLessonPassed = [...new Set(courseEnroll.idLessonPassed)];
        console.log('54 ====', !!courseEnroll.lessonPassed.find(id => id === lesson.id));
        if (!!courseEnroll.lessonPassed.find(id => id === lesson.id)) {
          mark = true;
        } else {
          mark = false;
        }
      } 
    })
    return mark;
  }
  
  getCourseById(id: number) {
    this.courseService.getCourse(id).subscribe(resData => {
      this.courseSelected = resData;
      this.getTeacher(this.courseSelected.teacherId);

      if (!!this.courseSelected) {
        this.courseSelected.sections?.forEach(section => {
          section.lessons.forEach(lesson => {
            lesson.urlSafeResourse = this.sanitizer.bypassSecurityTrustResourceUrl(lesson.urlVideo as string);
            lesson.isChecked = false;
          })
        });
        this.lessonSelected = this.courseSelected.sections![0].lessons[0];
        this.lessonSelected.isChecked = true;
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
    lesson!.isChecked = true;
    this.courseSelected.sections?.forEach(section => {
      section.lessons.forEach(less => {
        if (less.id !== lesson?.id) {
          console.log('unchecked lesson');
          less.isChecked = false;
        }
      })
    })
    console.log(this.courseSelected);
    
    this.lessonSelected = lesson;
    this.learningService.markLessonLearned(lesson?.id).subscribe(resData => {
      console.log('mark lesson learned', resData);
    })
  }

  backPrevious() {
    this.router.navigate(['/home/course-detail', this.courseSelected.id]);
    // this._location.back();
  }

}
