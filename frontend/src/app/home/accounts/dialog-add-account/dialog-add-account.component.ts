import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DeleteDialogComponent, DialogData } from 'src/app/shared/component/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-dialog-add-account',
  templateUrl: './dialog-add-account.component.html',
  styleUrls: ['./dialog-add-account.component.css']
})
export class DialogAddAccountComponent implements OnInit {
  @ViewChild('f')
  formCreateAcc!: NgForm;

  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) { }

  ngOnInit(): void {
  }

  addAccount(form: NgForm) {
    console.log('add acc', form);
  }
  onCancel() {
    this.dialogRef.close();
  }

}
