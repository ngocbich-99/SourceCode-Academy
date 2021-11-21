import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-topbar-unregistered',
  templateUrl: './topbar-unregistered.component.html',
  styleUrls: ['./topbar-unregistered.component.css']
})
export class TopbarUnregisteredComponent implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }
  onSignup() {
    this.router.navigate(['/signup'])
  }

}
