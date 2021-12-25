import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-setting-account',
  templateUrl: './setting-account.component.html',
  styleUrls: ['./setting-account.component.css']
})
export class SettingAccountComponent implements OnInit {
  formUserInfo: FormGroup = new FormGroup({});
  formPassword: FormGroup = new FormGroup({});
  constructor(
    private _location: Location,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formUserInfo = new FormGroup({
      'name': new FormControl('', Validators.required),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'phone': new FormControl('', [Validators.required]),
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

  }

  changePassword() {

  }

  backPrevious() {
    // this.router.navigate([".."]);
    this._location.back();
  }

}
