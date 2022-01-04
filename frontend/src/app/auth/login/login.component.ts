import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { AuthService } from '../auth.service';
import { SignUpComponent } from '../sign-up/sign-up.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});
  showPassword = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private toastService: ToastServiceCodex,
    public dialogRef: MatDialogRef<LoginComponent>,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'email': new FormControl('', [Validators.required, Validators.email]),
      'password': new FormControl('', [Validators.required, Validators.minLength(6)])
    })
  }

  onLogin() {
    // delete data old
    // this.authService.logout();
    
    if (!this.loginForm.invalid) {
      this.authService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe(resData => {
        console.log(resData);
        if (resData.message === 'Not found account' || resData.message === 'Username or password incorrect') {
          this.toastService.showToast('Email hoặc mật khẩu không đúng!', StatusToast.ERROR);
        } else {
            // luu token vao localStorage
            localStorage.setItem('codex-token', resData.data.accessToken);
            // check quyen router link
            if (resData.data.role === 'HOC_VIEN') {
              this.router.navigate(['/home/dashboard-student']);
            } else if (resData.data.role === 'GIANG_VIEN' || resData.data.role === 'ADMIN') {
              this.router.navigate(['/home/courses/all']);
            } else {
              this.router.navigate(['/home/main-page-unregistered']);
            }
            this.dialogRef.close();
            // luu user info vao store ngrx
        }
        
      }, error => {
        console.log(error);
          this.toastService.showToast('Có lỗi xảy ra.Đăng nhập không thành công!', StatusToast.ERROR);
      });
    } else {
      return;
    }
  }

  openDialogSignup() {
    this.dialogRef.close();
    const dialogRef = this.dialog.open(SignUpComponent, {
      width: '688px',
      height: '553px',
      panelClass: 'dialogSignup'
    });
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

}
