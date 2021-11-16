import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  settingAcc() {
    console.log('setting acc');
  }

  logout() {
    console.log('logout');
  }
}
