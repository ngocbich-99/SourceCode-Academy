import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  ) { }

  ngOnInit(): void {
  }

  settingAcc() {
    this.router.navigate(['/home/setting-account']);
  }

  logout() {
    this.router.navigate(['/login']);
  }
}
