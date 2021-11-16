import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { MaterialModule } from './material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LoginComponent } from './auth/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccountsComponent } from './home/accounts/accounts.component';
import { CategoryComponent } from './home/category/category.component';
import { CoursesComponent } from './home/courses/courses.component';
import { MainPageComponent } from './home/main-page/main-page.component';
import { QuestionsComponent } from './home/questions/questions.component';
import { TestsComponent } from './home/tests/tests.component';
import { FooterComponent } from './layout/footer/footer.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { TopbarComponent } from './layout/topbar/topbar.component';
import { HttpClientModule } from '@angular/common/http';
import { AllCourseComponent } from './home/courses/all-course/all-course.component';
import { CourseCategoryItemComponent } from './home/courses/course-category-item/course-category-item.component';
import { DialogAddCourseComponent } from './home/courses/dialog-add-course/dialog-add-course.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    MainPageComponent,
    CoursesComponent,
    CategoryComponent,
    TestsComponent,
    QuestionsComponent,
    AccountsComponent,
    FooterComponent, 
    TopbarComponent, 
    SidebarComponent, 
    AllCourseComponent, 
    CourseCategoryItemComponent, 
    DialogAddCourseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [DialogAddCourseComponent]
})
export class AppModule { }
