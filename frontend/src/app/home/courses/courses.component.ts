import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from '../category/category.mode';
import { DialogAddCourseComponent } from './dialog-add-course/dialog-add-course.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  listCategory: Category[] = [];

  constructor(
    public dialog: MatDialog,
    public route: ActivatedRoute,
    public categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['openDialog']) {
        const dialogRef = this.dialog.open(DialogAddCourseComponent, {
          width: '1041px',
          height: '646px',
        });
    
        dialogRef.afterClosed().subscribe(result => {
          console.log(result);
        });
      }
    })
    // get list category
    this.getListCategory();
  }
  
  dialogAddCourse() {
    const dialogRef = this.dialog.open(DialogAddCourseComponent, {
      width: '1041px',
      height: '646px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });
  }

  async getListCategory() {
    this.listCategory = await this.categoryService.getListCategory().toPromise();
  }

  getCategory() {

  }

}
