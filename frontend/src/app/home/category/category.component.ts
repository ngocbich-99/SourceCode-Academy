import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { CategoryService } from 'src/app/services/category.service';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';
import { Category } from './category.model';
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
    public router: Router,
    public route: ActivatedRoute,
    private toastService: ToastServiceCodex
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['openDialog']) {
        const dialogRef = this.dialog.open(DialogAddCategoryComponent, {
          width: '640px',
          height: '428px',
        });
        dialogRef.afterClosed().subscribe(rs => {
          if (!!rs) {
            // todo logic
            console.log(rs);
            const category: Category = {
              name: rs.nameCategory,
              description: rs.description,
              createdTime: moment().valueOf(),
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
              this.toastService.showToast('Tạo mới danh mục thành công!', StatusToast.SUCCESS);
            }, error => {
              console.log('error when add category', error);
              this.toastService.showToast('Tạo mới danh mục thất bại!', StatusToast.ERROR);
            })
          }
        })
      }
    })
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
          name: rs.nameCategory,
          description: rs.description,
          createdTime: moment().valueOf(),
        }
        this.listCategory.push(category);
        this.listCategory.forEach((cat, index) => {
          cat.stt = index + 1;
        });
        // update table data material
        this.dataSource.data = this.listCategory;
        // add category to db
        this.categoryService.createCategory(category).subscribe(resData => {
          category.id = resData.id;
          this.toastService.showToast('Tạo mới danh mục thành công!', StatusToast.SUCCESS);
        }, error => {
          console.log('error when add category', error);
          this.toastService.showToast('Tạo mới danh mục thất bại!', StatusToast.ERROR);
        })
      }
    })
  }

  detailCategory(category: Category) {
    this.router.navigate([`/home/category/${category.id}`]);    
  }

  deleteCategory(category: Category) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '520px',
      height: '252px',
      data: {name: category.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.categoryService.deleteCategoryById(category.id).subscribe(resData => {
          console.log('delete category', resData);
          this.listCategory.splice(this.listCategory.indexOf(category), 1);
          this.dataSource.data = this.listCategory;
          this.toastService.showToast('Xoá danh mục thành công!', StatusToast.SUCCESS);
        }, error => {
          console.log('delete category error',error);
          this.toastService.showToast('Xoá danh mục thất bại!', StatusToast.ERROR);
        });
      }
    });
  }
}
