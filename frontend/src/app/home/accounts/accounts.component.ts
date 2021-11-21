import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AccountService } from 'src/app/services/account.service';
import { DeleteDialogComponent } from 'src/app/shared/component/delete-dialog/delete-dialog.component';
import { Account } from './account.model';
import { DialogAddAccountComponent } from './dialog-add-account/dialog-add-account.component';
import { DialogInfoAccountComponent } from './dialog-info-account/dialog-info-account.component';
import * as moment from 'moment';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit, AfterViewInit {
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  // @ViewChild(MatPaginator, { static: true })paginator: MatPaginator;

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
  accountSelected: Account = {};

  constructor(
    public dialog: MatDialog,
    private accountService: AccountService
  ) { }
  
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    // this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.getListAccount();
  }
  doFilter(filterValue: any) {
    console.log(filterValue);
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getListAccount() {
    this.accountService.getListAccount().subscribe(resData => {
      this.listAccount = resData;
      this.dataSource.data = this.listAccount;
    }, error => {
      console.log('error get list acc',error);
    })
  }

  addTeacher() {
    const dialogRef = this.dialog.open(DialogAddAccountComponent, {
      width: '640px',
      height: '530px',
    });
    dialogRef.afterClosed().subscribe(rs => {
      if (!!rs) {
        const account: Account = {
          userName: rs.username,
          email: rs.email,
          phone: rs.phone,
          role: rs.role,
          isActivate: rs.isActivate,
          createdTime: moment().toISOString(),
          password: rs.password,
        }
        
        this.listAccount.push(account);
        this.dataSource.data = this.listAccount;

        this.accountService.createAccount(account).subscribe(resData => {
          console.log('save acc',resData);
        }, error => {
          console.log('error save acc', error);
          
        })
      }
    })
  }
  async updateAccount(idAcc: number) {
    await this.getAccountById(idAcc);

    const dialogRef = this.dialog.open(DialogInfoAccountComponent, {
      width: '640px',
      height: '530px',
      data: {account: this.accountSelected}
    });
    dialogRef.afterClosed().subscribe((rs: Account) => {
      if (!!rs) {
        const index = this.listAccount.findIndex(acc => acc.idAccount === rs.idAccount);
        this.listAccount[index] = rs;
        this.dataSource.data = this.listAccount;

        this.accountService.updateAccount(rs).subscribe(resData => {
          console.log('update acc', resData);
        })
      }
    })
  }
  async getAccountById(idAcc: number) {
    // this.accountService.getAccountById(idAcc).subscribe(resData => {
    //   this.accountSelected = resData;
    // });
    this.accountSelected = await this.accountService.getAccountById(idAcc).toPromise();
  }

  deleteAccount(account: Account) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '520px',
      height: '252px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.listAccount.splice(this.listAccount.indexOf(account), 1);
        this.dataSource.data = this.listAccount;

        this.accountService.deleteAccById(account.idAccount).subscribe(resData => {
          console.log('delete acc', resData);
        }, error => {
          console.log('delete acc error',error);
        });
      }
    });
  }

  convertLabelDisplay(role: string): string {
    if (role === 'TEACHER') {
      return 'Giảng viên';
    } else if (role === 'STUDENT'){
      return 'Học viên';
    } else {
      return 'Chưa có'
    }
  }
}

