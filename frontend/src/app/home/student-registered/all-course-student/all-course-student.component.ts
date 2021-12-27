import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from '../../category/category.model';

@Component({
  selector: 'app-all-course-student',
  templateUrl: './all-course-student.component.html',
  styleUrls: ['./all-course-student.component.css']
})
export class AllCourseStudentComponent implements OnInit {
  listCategory: Category[] = [];

  constructor(
    public categoryService: CategoryService,
  ) { }

  ngOnInit(): void {
    this.getListCategory();
  }
  async getListCategory() {
    // this.listCategory = await this.categoryService.getListCategory().toPromise();
    this.categoryService.getListCategory().subscribe(resData => {
      this.listCategory = resData;
    })
  }



}
