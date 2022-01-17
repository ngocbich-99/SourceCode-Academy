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
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';

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
    private accountService: AccountService,
    private toastCodexService: ToastServiceCodex
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
    if (!!listAcc) {
      listAcc?.forEach((acc, index) => {
        acc.stt = index + 1;
      });
      this.dataSourceActivate.data = listAcc;
      console.log(this.dataSourceActivate.data);
    }
  }
  getAccountLock() {
    this.accountService.getAccountLock().subscribe(resData => {
      console.log(resData);
      
      if (!!resData) {
        this.dataSourceLock.data = resData;
        resData?.forEach((acc, index) => {
          acc.stt = index + 1;
        });
      }
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
          fullName: rs.username,
          username: rs.email,
          email: rs.email,
          phone: rs.phone,
          role: rs.role,
          isActivate: rs.isActivate,
          createdTime: moment().valueOf(),
          password: rs.password,
        }
        this.accountService.createAccount(account).subscribe(resData => {
          if (resData.message === 'Email đã được sử dụng') {
            this.toastCodexService.showToast('Email đã được sử dụng!', StatusToast.ERROR);
          } else if (resData.message === 'Create account success') {
            this.toastCodexService.showToast('Tạo mới giảng viên thành công!', StatusToast.SUCCESS);
            // all list account
            this.listAccount.push(account);
            this.listAccount.forEach((acc, index) => {
              acc.stt = index + 1;
            })
            this.listAccount[this.listAccount.indexOf(account)].id = resData.data.id;
            this.dataSource.data = this.listAccount;

            console.log('list account', this.listAccount[this.listAccount.indexOf(account)]);
            
          }
        }, error => {
          console.log('error add gv', error);
          this.toastCodexService.showToast('Tạo mới giảng viên thất bại!', StatusToast.ERROR);
        });
        
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
        // goi api de update account trong csdl
        const accountReq: Account = {
          email: rs.email,
          fullName: rs.fullName,
          id: rs.id,
          isActivate: rs.isActivate,
          phone: rs.phone,
          role: rs.role,
          username: rs.email
        }
        this.accountService.updateAccount(accountReq).subscribe(resData => {
          console.log('update account', resData);
          if (resData.message === 'Update account success') {
            const index = this.listAccount.findIndex(acc => acc.id === rs.id);
            this.listAccount[index] = rs;
            this.listAccount.forEach((acc, index) => {
              acc.stt = index + 1;
            });
            this.dataSource.data = this.listAccount;
            this.toastCodexService.showToast('Cập nhật tài khoản thành công!', StatusToast.SUCCESS);
          }
        });

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
      data: {name: account.fullName}
    });

    dialogRef.afterClosed().subscribe(async result => {
      if (result === true) {
        this.accountService.deleteAccById(account.id).subscribe(resData => {
          console.log(resData);
          this.toastCodexService.showToast('Xoá giảng viên thành công!', StatusToast.SUCCESS);

          this.listAccount.splice(this.listAccount.indexOf(account), 1);
          this.dataSource.data = this.listAccount;
        }, error => {
          console.log(error);
          this.toastCodexService.showToast('Xoá giảng viên thất bại!', StatusToast.ERROR);
        });

        // get list account activate
        this.getAccountActivate();

        // get list account is locked
        this.getAccountLock();
      }
    });
  }

  convertLabelDisplay(role: string): string {
    if (role === 'GIANG_VIEN') {
      return 'Giảng viên';
    } else if (role === 'HOC_VIEN'){
      return 'Học viên';
    } else if (role === 'ADMIN') {
      return 'Quản trị viên';
    }
    else {
      return 'Chưa có'
    }
  }
}

