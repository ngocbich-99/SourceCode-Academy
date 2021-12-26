import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { User, UserInfo } from 'src/app/auth/user.model';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  userInfo: UserInfo = {};
  private userSub: Subscription = new Subscription;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    console.log('ngOnInit personal-info');
    this.getUserInfo();
  }

  getUserInfo() {
    this.userSub = this.authService.userInfoSubject.subscribe(userInfo => {
      this.userInfo = userInfo;
    })
  }

  settingAcc() {
    this.router.navigate(['/home/setting-account']);
  }

  logout() {
    this.router.navigate(['/login']);
    this.authService.logout();
  }

  ngOnDestroy() {
    if (this.userSub) {
      this.userSub.unsubscribe();
    }
  }
}
