import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';
import { UserInfo } from 'src/app/auth/user.model';
import { Subscription } from 'rxjs';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';

@Component({
  selector: 'app-setting-account',
  templateUrl: './setting-account.component.html',
  styleUrls: ['./setting-account.component.css']
})
export class SettingAccountComponent implements OnInit {
  formUserInfo: FormGroup = new FormGroup({});
  formPassword: FormGroup = new FormGroup({});
  userInfo: UserInfo = {};
  userSub: Subscription = new Subscription;

  incorrectOldPass = '';

  constructor(
    private _location: Location,
    private fb: FormBuilder,
    private authService: AuthService,
    private toastService: ToastServiceCodex
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  getUserInfo() {
    this.userSub = this.authService.userInfoSubject.subscribe(resData => {
      this.userInfo = resData;
    });
  }

  initForm() {
    this.getUserInfo();

    this.formUserInfo = new FormGroup({
      'name': new FormControl(this.userInfo.fullName, Validators.required),
      'email': new FormControl({disabled: true, value: this.userInfo.email}, [Validators.required, Validators.email]),
      'phone': new FormControl(this.userInfo.phone, [Validators.required]),
    })
    this.formPassword = this.fb.group({
      'oldPass': new FormControl('', [Validators.required, Validators.minLength(6)]),
      'newPass': new FormControl('', [Validators.required, Validators.minLength(6)]),
      'confirmPass': new FormControl('', [Validators.required, Validators.minLength(6)]),
    }, {
        validator: this.checkMatchPass('newPass', 'confirmPass')
    })
  }

  checkMatchPass(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
        const control = formGroup.controls[controlName];
        const matchingControl = formGroup.controls[matchingControlName];

        if (matchingControl.errors && !matchingControl.errors.notMatch) {
            // return if another validator has already found an error on the matchingControl
            return;
        }

        // set error on matchingControl if validation not match
        if (control.value !== matchingControl.value) {
            matchingControl.setErrors({notMatch: true});
        } else {
            matchingControl.setErrors(null);
        }
    }
}

  saveInfoUser() {
    this.userInfo.fullName = this.formUserInfo.value.name;
    this.userInfo.phone = this.formUserInfo.value.phone;

    this.authService.changeUserInfo(this.userInfo).subscribe(resData => {
      this.userInfo = resData;
      this.toastService.showToast('Cập nhật thông tin cá nhân thành công!', StatusToast.SUCCESS);
    }, error => {
      console.log('error update user info', error);
      this.toastService.showToast('Cập nhật thông tin cá nhân thất bại!', StatusToast.ERROR);
    })
  }

  changePassword() {
    if (this.formPassword.valid) {
      this.authService.changePassword(this.formPassword.value.oldPass, this.formPassword.value.newPass).subscribe(resData => {
        if (resData.message === 'Change password success') {
          this.toastService.showToast('Đổi mật khẩu thành công!', StatusToast.SUCCESS);
        } else if (resData.message === 'Old password incorrect') {
          this.incorrectOldPass = 'Mật khẩu cũ không đúng!';
          this.formPassword.get('oldPass')?.setErrors({'incorrect': true});
        }
      }, error => {
        console.log(error);
        this.toastService.showToast('Đổi mật khẩu thất bại!', StatusToast.ERROR);
      })
    }
  }

  onChangeOldPass() {
    this.incorrectOldPass = '';
  }

  backPrevious() {
    // this.router.navigate([".."]);
    this._location.back();
  }

  ngOnDestroy() {
    if (this.userSub) {
      this.userSub.unsubscribe();
    }
  }

}
