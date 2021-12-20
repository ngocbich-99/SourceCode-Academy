import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { Account } from 'src/app/home/accounts/account.model';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup = new FormGroup({});

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastService: ToastServiceCodex
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
    const accountReq: Account = {
      userName: this.signUpForm.value.name,
      email: this.signUpForm.value.email, 
      phone: '',
      role: 'HOC_VIEN',
      isActivate: true,
      createdTime: moment().valueOf(), //doi ve dang millisecond trong db
      password: this.signUpForm.value.password,
    }
    this.authService.signUp(accountReq).subscribe(resData => {
      console.log(resData);
      this.toastService.showToast('Đăng ký tài khoản thành công!', StatusToast.SUCCESS);
      this.router.navigate(['/login']);
    }, error => {
      console.log(error);
      this.toastService.showToast('Đăng ký tài khoản thất bại!', StatusToast.ERROR)
    })
  }

}
