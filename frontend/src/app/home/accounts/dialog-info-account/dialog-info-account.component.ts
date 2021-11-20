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
  updateAccForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<DialogInfoAccountComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {account: Account},
  ) { }

  ngOnInit(): void {
    this.updateAccForm = new FormGroup({
      'username': new FormControl(this.data.account.userName, Validators.required),
      'email': new FormControl(this.data.account.email, [Validators.required, Validators.email]),
      'phone': new FormControl(this.data.account.phone, [Validators.required]),
      'role': new FormControl({value: this.data.account.role, disabled: true}),
      'isActivate': new FormControl(true),
    });
  }

  updateAccount() {
    console.log(this.updateAccForm);
  }
  onCancel() {
    this.dialogRef.close();
  }

}
