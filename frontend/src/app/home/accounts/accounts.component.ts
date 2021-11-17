import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';
import { Account } from './account.model';
import { DialogAddAccountComponent } from './dialog-add-account/dialog-add-account.component';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit, AfterViewInit {
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns = [
    'stt',
    'nameUser',
    'email',
    'phoneNumber',
    'position',
    'status',
    'action'
  ];
  dataSource = new MatTableDataSource<Account>();
  listAccount: Account[] = [];

  constructor(
    public dialog: MatDialog
  ) { }
  
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.listAccount = [
      {
        id: 'acc1',
        stt: 1,
        nameUser: 'nguyen thi ngoc bich',
        email: 'ngocbich@gmail.com',
        phoneNumber: '0357172637',
        position: 'Hoc vien',
        isActivate: true
      },
      {
        id: 'acc2',
        stt: 2,
        nameUser: 'Dao Minh Duc',
        email: 'daoduc@gmail.com',
        phoneNumber: '099999999',
        position: 'Hoc vien',
        isActivate: false
      }
    ];
    this.dataSource.data = this.listAccount;

  }
  doFilter(filterValue: any) {
    console.log(filterValue);
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  addTeacher() {
    const dialogRef = this.dialog.open(DialogAddAccountComponent);
    dialogRef.afterClosed().subscribe(rs => {
      console.log(rs);
    })
  }

  updateAccount() {

  }

  deleteAccount() {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '520px',
      height: '252px',
      // data: {name: this.name, animal: this.animal},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }
}
