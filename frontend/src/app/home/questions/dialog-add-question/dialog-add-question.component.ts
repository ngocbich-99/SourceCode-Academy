import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from '../../category/category.model';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-dialog-add-question',
  templateUrl: './dialog-add-question.component.html',
  styleUrls: ['./dialog-add-question.component.css']
})
export class DialogAddQuestionComponent implements OnInit {
  createQuestionForm: FormGroup = new FormGroup({});

  listCategory: Category[] = [];
  listCourse: Course[] = [];

  constructor(
    public dialogRef: MatDialogRef<DialogAddQuestionComponent>,
    private categoryService: CategoryService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.createQuestionForm = new FormGroup({
      'category': new FormControl('', Validators.required),
      'idCourse': new FormControl('', Validators.required),
      'content': new FormControl('', Validators.required),
      'ans1': new FormControl('', Validators.required),
      'ans2': new FormControl('', Validators.required),
      'ans3': new FormControl('', Validators.required),
      'ans4': new FormControl('', Validators.required),
      'correctAns': new FormControl('', Validators.required),
    });
    this.getListCategory();
  }
  async getListCategory() {
    this.listCategory = await this.categoryService.getListCategory().toPromise();
    
    console.log(this.listCategory);
  }

  selectCategory(event: any) {
    console.log(event);
    
    const courses = this.listCategory.find(category => category.id === event.value)?.courses;
    if (!!courses) {
      this.listCourse = courses;
    } 
    console.log(this.listCourse);
  }

  selectCourse(event: any) {
  }

  openCategory() {
    window.open('/home/category?openDialog=true', '_blank');
  }

  openCourse() {
    window.open('/home/courses?openDialog=true', '_blank');
  }
  onCancel() {
    this.dialogRef.close();
  }
}
