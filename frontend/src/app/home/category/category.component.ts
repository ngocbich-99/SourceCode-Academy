import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { CategoryService } from 'src/app/services/category.service';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';
import { Category } from './category.mode';
import { DialogAddCategoryComponent } from './dialog-add-category/dialog-add-category.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  dataSource = new MatTableDataSource<Category>();
  displayedColumns = [
    'stt',
    'nameCategory',
    'numberCourse',
    'discription',
    'action'
  ];
  listCategory: Category[] = [];

  constructor(
    public dialog: MatDialog,
    public categoryService: CategoryService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.getListCategory();
  }
  getListCategory() {
    this.categoryService.getListCategory().subscribe(resData => {
      this.listCategory = resData;
      this.listCategory.forEach((category, index) => {
        category.stt = index + 1;
      });
      this.dataSource.data = this.listCategory;
    }, error => {
      console.log('error when get list category', error);
    })
  }
  addCategory() {
    const dialogRef = this.dialog.open(DialogAddCategoryComponent, {
      width: '640px',
      height: '428px',
    });
    dialogRef.afterClosed().subscribe(rs => {
      if (!!rs) {
        // todo logic
        console.log(rs);
        const category: Category = {
          nameCategory: rs.nameCategory,
          description: rs.description,
          createdTime: moment().valueOf(),
          courses: []
        }
        this.listCategory.push(category);
        this.listCategory.forEach((cat, index) => {
          cat.stt = index + 1;
        });
        // update table data material
        this.dataSource.data = this.listCategory;
        // add category to db
        this.categoryService.createCategory(category).subscribe(resData => {
          console.log('add category', resData);
        }, error => {
          console.log('error when add category', error);
        })
      }
    })
  }

  detailCategory(category: Category) {
    this.router.navigate([`/home/category/${category.idCategory}`]);
  }

  deleteCategory(category: Category) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '520px',
      height: '252px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.listCategory.splice(this.listCategory.indexOf(category), 1);
        this.dataSource.data = this.listCategory;

        this.categoryService.deleteCategoryById(category.idCategory).subscribe(resData => {
          console.log('delete category', resData);
        }, error => {
          console.log('delete category error',error);
        });
      }
    });
  }
}
