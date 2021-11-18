import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { HomePage } from './home/home.page';

const routes: Routes = [
  { 
    path: 'home', 
    loadChildren: () => import("./home/home.module").then((m) => m.HomePageModule)
  },
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full",
  },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
