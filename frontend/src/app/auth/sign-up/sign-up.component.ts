import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { SignUpReq } from '../auth.model';
import { AuthService } from '../auth.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup = new FormGroup({});
  showPassword = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastService: ToastServiceCodex,
    public dialogRef: MatDialogRef<SignUpComponent>,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    // create form
    this.signUpForm = new FormGroup({
      'name': new FormControl('', Validators.required),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'password': new FormControl('', [Validators.required, Validators.minLength(6)])
    })
  }
  OnSignUp() {
    if (this.signUpForm.invalid) {
      return;
    } else {
      const signUpReq: SignUpReq = {
        fullName: this.signUpForm.value.name,
        email: this.signUpForm.value.email, 
        password: this.signUpForm.value.password,
        reEnterPassword: this.signUpForm.value.password
      }
      this.authService.signUp(signUpReq).subscribe(resData => {
        console.log(resData);
        this.toastService.showToast('Đăng ký tài khoản thành công!', StatusToast.SUCCESS);
        this.dialogRef.close();
        
        // this.router.navigate(['/login']);
        // open dialog login
        const dialogRef = this.dialog.open(LoginComponent, {
          width: '688px',
          height: '553px',
          panelClass: 'dialogSignup'
        });
      }, error => {
        console.log(error);
        this.toastService.showToast('Đăng ký tài khoản thất bại!', StatusToast.ERROR)
      })
    }
  }

  openDialogLogin() {
    this.dialogRef.close();

    const dialogRef = this.dialog.open(LoginComponent, {
      width: '688px',
      height: '553px',
      panelClass: 'dialogSignup'
    });
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

}
