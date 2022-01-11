import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { Course } from '../courses/course.model';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { AccountService } from 'src/app/services/account.service';
import { Account } from '../accounts/account.model';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/auth/login/login.component';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {
  isCollapsed = false;
  courseSelected: Course = {};
  teacher: Account = {};
  userSub: Subscription = new Subscription;
  isProgressEnroll = false;

  constructor(
    private _location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private toastService: ToastServiceCodex,
    private authService: AuthService,
    private accountService: AccountService,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.getCourseById(params['id']);
    })
  }

  async getCourseById(id: number) {
    // this.courseService.getCourse(id).subscribe(resData => {
    //   this.courseSelected = resData;
    //   this.getTeacher(this.courseSelected.teacherId);
    //   console.log('course by id', resData);
    // })
    this.courseSelected = await this.courseService.getCourse(id).toPromise();

    // get info teacher
    this.getTeacher(this.courseSelected.teacherId);

    // check xem khoa hoc da duoc dang ky hoc chua
    // this.courseService.enrollCourse(this.courseSelected.id).subscribe(resData => {
    //   console.log('enroll course', resData);
    //   if (resData.message === 'Tài khoản đã đăng ký khóa học') {
    //     this.router.navigate(['/home/learning-course'], {queryParams: {id: this.courseSelected.id}});
    //   } else if (resData.message === 'Enroll course success') {
    //     this.isProgressEnroll = true;
    //     setTimeout(() => {
    //       this.isProgressEnroll = false;
    //       this.toastService.showToast('Đăng ký khoá học thành công!', StatusToast.SUCCESS);
    //       this.router.navigate(['/home/learning-course'], {queryParams: {id: this.courseSelected.id}});
    //     }, 5000)
    //   }
    // })
  }

  getTeacher(id?: number) {
    this.accountService.getAccountById(id).subscribe(resData => {
      this.teacher = resData;
      console.log('get teacher', resData);
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
            // this.toastService.showToast('Tài khoản đã đăng ký khóa học!', StatusToast.ERROR);
            this.router.navigate(['/home/learning-course'], {queryParams: {id: this.courseSelected.id}});
          } else if (resData.message === 'Enroll course success') {
            this.isProgressEnroll = true;
            setTimeout(() => {
              this.isProgressEnroll = false;
              this.toastService.showToast('Đăng ký khoá học thành công!', StatusToast.SUCCESS);
              this.router.navigate(['/home/learning-course'], {queryParams: {id: this.courseSelected.id}});
            }, 5000)
          }
        })
      } else if (userInfo.role == undefined) {
        const dialogRef = this.dialog.open(LoginComponent, {
          width: '688px',
          height: '553px',
          panelClass: 'dialogSignup'
        });
        dialogRef.afterClosed().subscribe(data => {
          console.log(data);
        })
      }
    })
    
  }

}
