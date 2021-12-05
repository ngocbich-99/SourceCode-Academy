import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-add-category',
  templateUrl: './dialog-add-category.component.html',
  styleUrls: ['./dialog-add-category.component.css']
})
export class DialogAddCategoryComponent implements OnInit {
  createCategoryForm: FormGroup= new FormGroup({});

  constructor(
    public dialogRef: MatDialogRef<DialogAddCategoryComponent>,
  ) { }

  ngOnInit(): void {
    this.createCategoryForm = new FormGroup({
      'nameCategory': new FormControl('', Validators.required),
      'discription': new FormControl('', Validators.required)
    })
  }

  onCancel() {
    this.dialogRef.close();
  }

}
