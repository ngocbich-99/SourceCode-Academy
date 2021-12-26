import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CourseService } from 'src/app/services/course.service';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';
import { Course } from '../course.model';
import { DialogInfoCourseComponent } from '../dialog-info-course/dialog-info-course.component';

@Component({
  selector: 'app-course-category-item',
  templateUrl: './course-category-item.component.html',
  styleUrls: ['./course-category-item.component.css']
})
export class CourseCategoryItemComponent implements OnInit {
  @Input() courses!: Course[];
  
  constructor(
    public dialog: MatDialog,
    private courseService: CourseService,
    private toastServiceCodex: ToastServiceCodex
  ) { }

  ngOnInit(): void {
    console.log('ngOnInit', this.courses);
  }

  updateCourse(course: Course) {
    // get course detail
    const dialogRef = this.dialog.open(DialogInfoCourseComponent, {
      width: '1041px',
      height: '646px',
      data: {courseSelected: course}
    });
    console.log(course);
    
    dialogRef.afterClosed().subscribe(rs => {
      console.log(rs);
    })
    // update course
  }
  deleteCourse(course: Course) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '520px',
      height: '252px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result) {
        this.courseService.deleteCourse(course.id).subscribe(resData => {
          this.courses.splice(this.courses.indexOf(course), 1);

          this.toastServiceCodex.showToast('Xoá khoá học thành công!', StatusToast.SUCCESS);
        }, error => {
          console.log(error);
          this.toastServiceCodex.showToast('Xoá khoá học thất bại!', StatusToast.ERROR);
        })
      }
    });
  }

}
