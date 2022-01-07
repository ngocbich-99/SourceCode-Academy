import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { CourseService } from 'src/app/services/course.service';
import { Category } from '../../category/category.model';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-courses-unregistered',
  templateUrl: './courses-unregistered.component.html',
  styleUrls: ['./courses-unregistered.component.css']
})
export class CoursesUnregisteredComponent implements OnInit {
  listCategory: Category[] = [];
  listCourse: Course[] = [];
  
  constructor(
    public categoryService: CategoryService,
    public route: ActivatedRoute,
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.getListCategory();

    this.route.queryParams.subscribe(params => {
      if (params['category'] === 'all') {
        // get list course public
        this.getCoursesPublic();
      } else {
        console.log(params);
        this.getCoursePublicByNameCategory(params['category']);
      }
    })
  }

  getCoursesPublic() {
    this.courseService.getCoursesPublic().subscribe(resData => {
      this.listCourse = resData.data;
    })
  }

  getCoursePublicByNameCategory(name?: string) {
    this.categoryService.getCoursePublicByCategoryName([name as string]).subscribe(resData => {
      console.log(resData);
      this.listCourse = resData.data;
    })
  }

  async getListCategory() {
    // this.listCategory = await this.categoryService.getListCategory().toPromise();
    this.categoryService.getListCategory().subscribe(resData => {
      this.listCategory = resData;
      console.log(this.listCategory);
    })
  }

}
