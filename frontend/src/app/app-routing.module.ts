import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { AccountsComponent } from './home/accounts/accounts.component';
import { CategoryComponent } from './home/category/category.component';
import { CoursesComponent } from './home/courses/courses.component';
import { MainPageComponent } from './home/main-page/main-page.component';
import { QuestionsComponent } from './home/questions/questions.component';
import { TestsComponent } from './home/tests/tests.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'courses', component: CoursesComponent},
  { path: 'category', component: CategoryComponent},
  { path: 'tests', component: TestsComponent},
  { path: 'questions', component: QuestionsComponent},
  { path: 'accounts', component: AccountsComponent},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignUpComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
