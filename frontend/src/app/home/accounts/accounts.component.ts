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
import { MatSlideToggle } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit, AfterViewInit {
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  @ViewChild('paginatorAll', { static: true }) paginatorAll!: MatPaginator;
  @ViewChild('paginatorActivate', {static: true}) paginatorActivate!: MatPaginator;
  @ViewChild('paginatorLock', {static: true}) paginatorLock!: MatPaginator;

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
  dataSourceActivate = new MatTableDataSource<Account>();
  dataSourceLock = new MatTableDataSource<Account>();

  listAccount: Account[] = [];

  accountSelected: Account = {};

  constructor(
    public dialog: MatDialog,
    private accountService: AccountService
  ) { }
  
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginatorAll;
    this.dataSourceActivate.paginator = this.paginatorActivate;
    this.dataSourceLock.paginator = this.paginatorLock;
  }

  ngOnInit(): void {
    this.getListAccount();
    this.getAccountActivate();
    this.getAccountLock();
  }
  doFilter(filterValue: any) {
    this.dataSource.filter = filterValue.value.trim().toLowerCase();
  }
  getListAccount() {
    this.accountService.getListAccount().subscribe(resData => {
      // get all list account
      this.listAccount = resData;
      this.listAccount.forEach((acc, index) => {
        acc.stt = index + 1;
      })
      this.dataSource.data = this.listAccount;

      // // get list account activate
      // this.dataSourceActivate.data = this.listAccount.filter(account => account.isActivate === true);

      // get list account is locked
      // this.dataSourceLock.data = this.listAccount.filter(account => account.isActivate === false);
    }, error => {
      console.log('error get list acc',error);
    })
  }
  async getAccountActivate() {
    const listAcc = await this.accountService.getAccountActivate().toPromise();
    listAcc.forEach((acc, index) => {
      acc.stt = index + 1;
    });
    this.dataSourceActivate.data = listAcc;
    console.log(this.dataSourceActivate.data);
  }
  getAccountLock() {
    this.accountService.getAccountLock().subscribe(resData => {
      resData.forEach((acc, index) => {
        acc.stt = index + 1;
      });
      this.dataSourceLock.data = resData;
    })
  }
  async toggleStatusAccount(toggle: MatSlideToggle, account: Account) {
    account.isActivate = !toggle.checked;
    await this.accountService.updateAccount(account).toPromise();

    // get list account activate
    this.getAccountActivate();

    // get list account is locked
    this.getAccountLock();

  }

  addTeacher() {
    const dialogRef = this.dialog.open(DialogAddAccountComponent, {
      width: '640px',
      height: '530px',
    });
    dialogRef.afterClosed().subscribe(async rs => {
      if (!!rs) {
        const account: Account = {
          userName: rs.username,
          email: rs.email,
          phone: rs.phone,
          role: rs.role,
          isActivate: rs.isActivate,
          createdTime: moment().valueOf(),
          password: rs.password,
        }
        this.listAccount.push(account);
        this.listAccount.forEach((acc, index) => {
          acc.stt = index + 1;
        })

        const accountRes = await this.accountService.createAccount(account).toPromise();
        this.listAccount[this.listAccount.indexOf(account)].idAccount = accountRes.idAccount;

        // all list account
        this.dataSource.data = this.listAccount;

        // get list account activate
        this.getAccountActivate();

        // get list account is locked
        this.getAccountLock();
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
    dialogRef.afterClosed().subscribe(async (rs: Account) => {
      if (!!rs) {
        const index = this.listAccount.findIndex(acc => acc.idAccount === rs.idAccount);
        this.listAccount[index] = rs;
        this.listAccount.forEach((acc, index) => {
          acc.stt = index + 1;
        });

        // goi api de update account trong csdl
        await this.accountService.updateAccount(rs).toPromise();
        
        this.dataSource.data = this.listAccount;

        // get list account activate
        this.getAccountActivate();

        // get list account is locked
        this.getAccountLock();
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

    dialogRef.afterClosed().subscribe(async result => {
      if (result === true) {
        this.listAccount.splice(this.listAccount.indexOf(account), 1);
        this.dataSource.data = this.listAccount;
        
        const res = await this.accountService.deleteAccById(account.idAccount).toPromise();
        console.log(res);

        // get list account activate
        this.getAccountActivate();

        // get list account is locked
        this.getAccountLock();
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

