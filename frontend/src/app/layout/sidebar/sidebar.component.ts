import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';
import { Location } from '@angular/common';
import { User } from 'src/app/auth/user.model';
import { AuthService } from 'src/app/auth/auth.service';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  activeTab: string = '';
  role: string | undefined = ''; 
  closed$ = new Subject<any>();
  userSub: Subscription = new Subscription;

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

    this.userSub = this.authService.userInfoSubject.subscribe((userInfo) => {
      this.role = userInfo.role;
      console.log(this.role);

      if (this.role === 'HOC_VIEN') {
        this.activeTab = 'dashboard';
      } else if (this.role === 'ADMIN' || this.role === 'GIANG_VIEN') {
        this.activeTab = 'courses';
      } else if (this.role == undefined) {
        this.activeTab = 'dashboard-unregistered';
      }
    })
    
  }

  onTabChange(nameTab: string) {
    this.activeTab = nameTab;
  }

  ngOnDestroy() {
    if (this.userSub) {
      this.userSub.unsubscribe();
    }
  }
}
