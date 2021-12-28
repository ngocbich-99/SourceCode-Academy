import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginComponent } from 'src/app/auth/login/login.component';
import { SignUpComponent } from 'src/app/auth/sign-up/sign-up.component';

@Component({
  selector: 'app-topbar-unregistered',
  templateUrl: './topbar-unregistered.component.html',
  styleUrls: ['./topbar-unregistered.component.css']
})
export class TopbarUnregisteredComponent implements OnInit {

  constructor(
    private router: Router,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
  }
  onSignup() {
    // this.router.navigate(['/signup'])
    // dialog signup
    const dialogRef = this.dialog.open(SignUpComponent, {
      width: '688px',
      height: '553px',
      panelClass: 'dialogSignup'
    });
    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
    })
  }

  onLogin() {
    // this.router.navigate(['/login'])
    // dialog login
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '688px',
      height: '553px',
      panelClass: 'dialogSignup'
    });
    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
    })
  }

}
