import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DeleteDialogComponent, DialogData } from 'src/app/shared/component/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-dialog-add-account',
  templateUrl: './dialog-add-account.component.html',
  styleUrls: ['./dialog-add-account.component.css']
})
export class DialogAddAccountComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) { }

  ngOnInit(): void {
  }

  addAccount() {
    
  }
  onCancel() {
    this.dialogRef.close();
  }

}
