import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import * as moment from 'moment';
import { SignUpComponent } from 'src/app/auth/sign-up/sign-up.component';
import { CourseService } from 'src/app/services/course.service';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-main-unregistered',
  templateUrl: './main-unregistered.component.html',
  styleUrls: ['./main-unregistered.component.css']
})
export class MainUnregisteredComponent implements OnInit {
  listCourse: Course[] = [];

  constructor(
    private courseService: CourseService,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.getListCoursePublic();
  }

  getListCoursePublic() {
    this.courseService.getCoursesPublic().subscribe(resData => {
      this.listCourse = resData.data;
    });
  }

  getCourseNew(): Course[] {
    return this.listCourse.filter(course => moment().valueOf() - Number(course.createdTime) > 1.728e+8);
  }

  getCoursePopular(): Course[] {
    this.listCourse.sort((a, b) => {
      return Number(b.subscriberNumber) - Number(a.subscriberNumber)
    })
    return this.listCourse.slice(0, 5);
  }

  openSignup() {
    const dialogRef = this.dialog.open(SignUpComponent, {
      width: '688px',
      height: '553px',
      panelClass: 'dialogSignup'
    });
    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
    })
  }

}
