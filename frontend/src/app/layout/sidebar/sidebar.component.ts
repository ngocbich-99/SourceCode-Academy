import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  constructor() { }
  activeTab = 'dashboard';
  role='ADMIN'

  ngOnInit(): void {
  }
  onTabChange(nameTab: string) {
    this.activeTab = nameTab;
  }
}
