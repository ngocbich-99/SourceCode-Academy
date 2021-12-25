import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/auth/user.model';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  userInfo: User = {};

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.userInfo = user;
    })
  }

  settingAcc() {
    console.log('setting acc');
    this.router.navigate(['/home/setting-account']);
  }

  logout() {
    console.log('logout');
    this.router.navigate(['/login']);
  }
}
