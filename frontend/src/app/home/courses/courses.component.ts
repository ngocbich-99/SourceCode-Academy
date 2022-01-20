import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { CourseService } from 'src/app/services/course.service';
import { Category } from '../category/category.model';
import { Course } from './course.model';
import { DialogAddCourseComponent } from './dialog-add-course/dialog-add-course.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  listCategory: Category[] = [];
  listCourseAll: Course[] = [];
  listCoursePublic: Course[] = [];
  listCoursePrivate: Course[] = [];
  userId: number = 0;

  constructor(
    public dialog: MatDialog,
    public route: ActivatedRoute,
    public categoryService: CategoryService,
    public courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.userId = JSON.parse((localStorage.getItem('userInfo') as string)).id;

    this.route.queryParams.subscribe(params => {
      if (params['openDialog']) {
        const dialogRef = this.dialog.open(DialogAddCourseComponent, {
          width: '1041px',
          height: '665px',
        });
    
        dialogRef.afterClosed().subscribe(result => {
          console.log(result);
        });
      }
      if (params['category'] === 'all') {
        this.getListCourse();
        this.getListCoursePublic();
        this.getListCoursePrivate();
      } else {
        this.getCourseByNameCategory(params['category']);
      }
    })
    // get list category
    this.getListCategory();
  }

  getCourseByNameCategory(name?: string) {
    this.categoryService.getCoursesByCategory([name as string]).subscribe(resData => {
      this.listCourseAll = resData.data.filter(course => course.teacherId === this.userId);
      this.listCoursePrivate = this.listCourseAll.filter(course => course.status === false);
      this.listCoursePublic = this.listCourseAll.filter(course => course.status === true);
    })
  }
  
  dialogAddCourse() {
    const dialogRef = this.dialog.open(DialogAddCourseComponent, {
      width: '1041px',
      height: '665px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('res dialog add course', result);
      if (!!result) {
        this.listCourseAll.push(result.data)
      }
    });
  }

  async getListCategory() {
    // this.listCategory = await this.categoryService.getListCategory().toPromise();
    this.categoryService.getListCategory().subscribe(resData => {
      this.listCategory = resData;
    })
  }

  async getListCourse() {
    this.listCourseAll = await this.courseService.getListCourse().toPromise();
    this.listCourseAll = this.listCourseAll.filter(course => course.teacherId === this.userId);
    console.log('list course', this.listCourseAll);
  }

  getListCoursePublic() {
    this.courseService.getCoursesPublic().subscribe(resData => {
      this.listCoursePublic = resData.data.filter(course => course.teacherId === this.userId);
    })
  }

  getListCoursePrivate() {
    this.courseService.getCoursesPrivate().subscribe(resData => {
      this.listCoursePrivate = resData.data.filter(course => course.teacherId === this.userId);;
    })
  }

  getCategory() {

  }

}
