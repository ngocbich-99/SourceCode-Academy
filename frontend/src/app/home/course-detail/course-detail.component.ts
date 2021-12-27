import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { Course } from '../courses/course.model';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {
  isCollapsed = false;
  courseSelected: Course = {};
  userSub: Subscription = new Subscription;

  constructor(
    private _location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private toastService: ToastServiceCodex,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.getCourseById(params['id']);
    })
  }

  getCourseById(id: number) {
    this.courseService.getCourse(id).subscribe(resData => {
      this.courseSelected = resData;
      console.log('course by id', resData);
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

  enrollmentCourse() {
    this.userSub = this.authService.userInfoSubject.subscribe((userInfo) => {
      if (userInfo.role === 'HOC_VIEN') {
        this.courseService.enrollCourse(this.courseSelected.id).subscribe(resData => {
          console.log('enroll course', resData);
          if (resData.message === 'Tài khoản đã đăng ký khóa học') {
            this.toastService.showToast('Đăng ký khoá học thành công!', StatusToast.SUCCESS);
            this.router.navigate(['/home/learning-course']);
          }
        })
      } else if (userInfo.role == undefined) {
        this.router.navigate(['/signup']);
      }
    })
    
  }

}
