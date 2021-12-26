import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { ToastrService } from 'ngx-toastr';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { Category } from '../category.model';
import { Course } from '../../courses/course.model';

@Component({
  selector: 'app-detail-category',
  templateUrl: './detail-category.component.html',
  styleUrls: ['./detail-category.component.css']
})
export class DetailCategoryComponent implements OnInit {
  editCategoryForm: FormGroup = new FormGroup({});
  categoryId: number = 0;
  category: Category = {};
  listCourse: Course[] = [];

  constructor(
    private _location: Location,
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private toastService: ToastServiceCodex,

  ) { }

  ngOnInit(): void {
     // khoi tao form
    this.editCategoryForm = new FormGroup({
      'nameCategory': new FormControl('', Validators.required),
      'description': new FormControl('', Validators.required)
    });

    // lay id category tu param truyen tren url
    this.route.params.subscribe((params: Params) => {
      this.categoryId = params['id'];
    });
    // goi service get thong tin chi tiet cua category
    this.getCategoryById();
  }

  backPrevious() {
    this._location.back();
  }

  async getCategoryById() {
    // this.categoryService.getCategoryById(this.categoryId).subscribe(resData => {
    //   this.category = resData;
    // });
    this.category = await this.categoryService.getCategoryById(this.categoryId).toPromise();

    this.editCategoryForm.get('nameCategory')?.setValue(this.category?.name);
    this.editCategoryForm.get('description')?.setValue(this.category?.description);

    // get list course by category
    this.getCoursesByCategory();
  }

  getCoursesByCategory() {
    this.categoryService.getCoursesByCategory([this.category?.name as string]).subscribe(resData => {
      this.listCourse = resData.data;
    })
  }

  getCourseBasic(): Course[] {
    return this.listCourse.filter(course => course.level === 1);
  }

  getCourseAdvance(): Course[] {
    return this.listCourse.filter(course => course.level === 2);
  }

  editCategory() {
    if (this.category.name !== this.editCategoryForm.value.nameCategory 
      || this.category.description !== this.editCategoryForm.value.description) {
      this.category.name = this.editCategoryForm.value.nameCategory;
      this.category.description = this.editCategoryForm.value.description;

      this.categoryService.updateCategory(this.category).subscribe(resData => {
        console.log(resData);
        this.toastService.showToast('Cập nhật danh mục thành công!', StatusToast.SUCCESS);
      }, error => {
        console.log(error);
        this.toastService.showToast('Cập nhật danh mục thất bại!', StatusToast.ERROR);
      })
    }
  }
}
