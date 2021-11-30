import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {
  isCollapsed = false;

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
