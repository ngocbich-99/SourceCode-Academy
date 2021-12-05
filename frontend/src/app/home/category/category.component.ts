import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
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

  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
  }
  addCategory() {
    const dialogRef = this.dialog.open(DialogAddCategoryComponent, {
      width: '640px',
      height: '428px',
    });
    dialogRef.afterClosed().subscribe(rs => {
      if (!!rs) {
        // todo logic
      }
    })
  }

  detailCategory() {

  }

  deleteCategory() {

  }
}
