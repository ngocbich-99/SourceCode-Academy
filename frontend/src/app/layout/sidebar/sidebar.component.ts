import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/auth/auth.service';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  activeTab: string = '';
  role: string | undefined; 
  closed$ = new Subject<any>();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private location: Location,
    private authService: AuthService
  ) { 
  }

  ngOnInit(): void {
    // this.location.onUrlChange((val) => {
    //   const url = val.split('?')[0];
    //   this.activeTab = url.split('/')[2];
    //   console.log(this.activeTab);
    // })
    this.authService.currentUser.subscribe(userInfo => {
      this.role = userInfo.role;
      if (this.role === 'HOC_VIEN') {
        this.activeTab = 'dashboard';
      } else if (this.role === 'ADMIN' || this.role === 'GIANG_VIEN') {
        this.activeTab = 'courses';
      } else {
        this.activeTab = 'dashboard';
      }
    })
  }

  onTabChange(nameTab: string) {
    this.activeTab = nameTab;
  }
}
