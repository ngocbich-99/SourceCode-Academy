import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  constructor() { }
  activeTab = 'courses';

  ngOnInit(): void {
  }
  onTabChange(nameTab: string) {
    console.log(nameTab);
    this.activeTab = nameTab;
  }
}
