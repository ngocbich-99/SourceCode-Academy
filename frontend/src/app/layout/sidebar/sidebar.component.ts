import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { Location } from '@angular/common';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  activeTab = 'dashboard';
  role='USER';
  closed$ = new Subject<any>();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) { 
  }

  ngOnInit(): void {
    // this.location.onUrlChange((val) => {
    //   const url = val.split('?')[0];
    //   this.activeTab = url.split('/')[2];
    //   console.log(this.activeTab);
    // })
  }

  onTabChange(nameTab: string) {
    this.activeTab = nameTab;
  }
}
