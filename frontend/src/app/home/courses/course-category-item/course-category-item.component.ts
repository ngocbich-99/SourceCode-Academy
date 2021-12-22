import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-course-category-item',
  templateUrl: './course-category-item.component.html',
  styleUrls: ['./course-category-item.component.css']
})
export class CourseCategoryItemComponent implements OnInit {
  animal: string = '';
  name: string = '';

  constructor(
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  updateCourse() {
    
  }
  deleteCourse() {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '520px',
      height: '252px',
      data: {name: this.name, animal: this.animal},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }

}
