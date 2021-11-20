import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Account } from '../account.model';

export interface DialogData {
  account: Account;
}
@Component({
  selector: 'app-dialog-add-account',
  templateUrl: './dialog-add-account.component.html',
  styleUrls: ['./dialog-add-account.component.css']
})
export class DialogAddAccountComponent implements OnInit {
  createAccForm: FormGroup;
  account: Account = {
    idAccount: 1,
  };

  constructor(
    public dialogRef: MatDialogRef<DialogAddAccountComponent>,
    // @Inject(MAT_DIALOG_DATA) public data: ,
  ) { }

  ngOnInit(): void {
    this.createAccForm = new FormGroup({
      'username': new FormControl('', Validators.required),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'phone': new FormControl('', [Validators.required]),
      'password': new FormControl('', [Validators.required]),
      'role': new FormControl('TEACHER'),
      'isActivate': new FormControl(true),
    });
  }
  
  onCancel() {
    this.dialogRef.close();
  }

}
