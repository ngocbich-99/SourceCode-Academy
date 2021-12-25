import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Location } from '@angular/common';
import { User } from 'src/app/auth/user.model';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  activeTab: string = '';
  role: string | undefined = ''; 
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

    let data: string = localStorage.getItem('userInfo') as string;
    const user: User = JSON.parse(data);
    if (!!user) {
      this.role = user.role;
    } else {
      this.role = '';
    }

    if (this.role === 'HOC_VIEN' || this.role === '') {
      this.activeTab = 'dashboard';
    } else if (this.role === 'ADMIN' || this.role === 'GIANG_VIEN') {
      this.activeTab = 'courses';
    } 

    console.log('sidebar', this.role, this.activeTab);
    
    
  }

  onTabChange(nameTab: string) {
    this.activeTab = nameTab;
  }
}
