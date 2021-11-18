import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-setting-account',
  templateUrl: './setting-account.component.html',
  styleUrls: ['./setting-account.component.css']
})
export class SettingAccountComponent implements OnInit {

  constructor(
    private _location: Location
  ) { }

  ngOnInit(): void {
  }

  backPrevious() {
    // this.router.navigate([".."]);
    this._location.back();
  }

}
