import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {
  isCollapsed = false;

  constructor(
    private _location: Location,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
  }

  backPrevious() {
    // this.router.navigate([".."]);
    this._location.back();
  }

  enrollmentCourse() {
    this.router.navigate(['/home/learning-course']);
  }

}
