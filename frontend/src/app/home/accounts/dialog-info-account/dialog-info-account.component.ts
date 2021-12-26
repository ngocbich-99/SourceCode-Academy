import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';
import { Account } from '../account.model';

@Component({
  selector: 'app-dialog-info-account',
  templateUrl: './dialog-info-account.component.html',
  styleUrls: ['./dialog-info-account.component.css']
})
export class DialogInfoAccountComponent implements OnInit {
  updateAccForm: FormGroup = new FormGroup({});

  constructor(
    public dialogRef: MatDialogRef<DialogInfoAccountComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {account: Account},
  ) { }

  ngOnInit(): void {
    this.updateAccForm = new FormGroup({
      'fullName': new FormControl(this.data.account.fullName, Validators.required),
      'email': new FormControl(this.data.account.email, [Validators.required, Validators.email]),
      'phone': new FormControl(this.data.account.phone, [Validators.required]),
      'role': new FormControl({value: this.convertLabelDisplay(this.data.account.role), disabled: true}),
      'isActivate': new FormControl(this.data.account.isActivate),
    });
  }

  updateAccount() {
    this.data.account.fullName = this.updateAccForm.value.fullName;
    this.data.account.email = this.updateAccForm.value.email;
    this.data.account.phone = this.updateAccForm.value.phone;
    this.data.account.isActivate = this.updateAccForm.value.isActivate;
  }
  onCancel() {
    this.dialogRef.close();
  }
  convertLabelDisplay(role?: string): string {
    if (role === 'GIANG_VIEN') {
      return 'Giảng viên';
    } else if (role === 'HOC_VIEN'){
      return 'Học viên';
    } else if (role === 'ADMIN') {
      return 'Quản trị viên';
    } else {
      return 'Chưa có';
    }
  }

}
