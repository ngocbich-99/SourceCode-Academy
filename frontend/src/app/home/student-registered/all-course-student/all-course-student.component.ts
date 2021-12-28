import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { CourseService } from 'src/app/services/course.service';
import { Category } from '../../category/category.model';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-all-course-student',
  templateUrl: './all-course-student.component.html',
  styleUrls: ['./all-course-student.component.css']
})
export class AllCourseStudentComponent implements OnInit {
  listCategory: Category[] = [];
  listCourse: Course[] = [];
  categorySelected?: Category = {};

  constructor(
    public categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    console.log('ngOnInit all-course-student');
    
    this.getListCategory();
    
    this.route.queryParams.subscribe((params: Params) => {
      if (params['category'] === 'all') {
        this.courseService.getCoursesPublic().subscribe(resData => {
          this.listCourse = resData.data;
        })
      } else {
        this.getCourseByNameCategory(params['category']);
      }
    })
  }
  async getListCategory() {
    // this.listCategory = await this.categoryService.getListCategory().toPromise();
    this.categoryService.getListCategory().subscribe(resData => {
      this.listCategory = resData;
    })
  }
  changeCategory(category?: Category) {
    // this.router.navigate(['home', 'all-course-student', name]);
    this.getCourseByNameCategory(category?.name);
    this.categorySelected = category;
  }
  getCourseByNameCategory(name?: string) {
    this.categoryService.getCoursesByCategory([name as string]).subscribe(resData => {
      this.listCourse = resData.data.filter(course => course.status === true);
    })
  }



}
