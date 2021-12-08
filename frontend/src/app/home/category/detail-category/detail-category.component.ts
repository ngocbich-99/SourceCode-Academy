import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from '../category.mode';

@Component({
  selector: 'app-detail-category',
  templateUrl: './detail-category.component.html',
  styleUrls: ['./detail-category.component.css']
})
export class DetailCategoryComponent implements OnInit {
  editCategoryForm: FormGroup = new FormGroup({});
  categoryId: number = 0;
  category: Category = {};

  constructor(
    private _location: Location,
    private route: ActivatedRoute,
    private categoryService: CategoryService
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
    console.log(this.category);

    this.editCategoryForm.get('nameCategory')?.setValue(this.category?.nameCategory);
    this.editCategoryForm.get('description')?.setValue(this.category?.description);
  }

  editCategory() {
    this.category.nameCategory = this.editCategoryForm.value.nameCategory;
    this.category.description = this.editCategoryForm.value.description;

    this.categoryService.updateCategory(this.category).subscribe(resData => {
      console.log(resData);
    })
  }
}
