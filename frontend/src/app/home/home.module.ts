import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FlexLayoutModule } from "@angular/flex-layout";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { FooterComponent } from "../layout/footer/footer.component";
import { SidebarComponent } from "../layout/sidebar/sidebar.component";
import { MaterialModule } from "../material.module";
import { SharedModule } from "../shared/shared.module";
import { AccountsComponent } from "./accounts/accounts.component";
import { DialogAddAccountComponent } from "./accounts/dialog-add-account/dialog-add-account.component";
import { CategoryComponent } from "./category/category.component";
import { CourseCategoryItemComponent } from "./courses/course-category-item/course-category-item.component";
import { CoursesComponent } from "./courses/courses.component";
import { DialogAddCourseComponent } from "./courses/dialog-add-course/dialog-add-course.component";
import { HomePageRoutingModule } from "./home-routing.module";
import { HomePage } from "./home.page";
import { QuestionsComponent } from "./questions/questions.component";
import { SettingAccountComponent } from "./setting-account/setting-account.component";
import { TestsComponent } from "./tests/tests.component";
import { DialogInfoAccountComponent } from './accounts/dialog-info-account/dialog-info-account.component';
import { MainUnregisteredComponent } from './Unregistered/main-unregistered/main-unregistered.component';
import { CoursesUnregisteredComponent } from './Unregistered/courses-unregistered/courses-unregistered.component';
import { AboutUsComponent } from './Unregistered/about-us/about-us.component';
import { DashboardStudentComponent } from './student-registered/dashboard-student/dashboard-student.component';
import { CoursesStudentComponent } from './student-registered/courses-student/courses-student.component';
import { AllCourseStudentComponent } from './student-registered/all-course-student/all-course-student.component';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { TabsModule } from "ngx-bootstrap/tabs";
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CourseDetailComponent } from './course-detail/course-detail.component';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { LearningCourseComponent } from './course-detail/learning-course/learning-course.component';
import { DialogAddCategoryComponent } from "./category/dialog-add-category/dialog-add-category.component";
import { DetailCategoryComponent } from "./category/detail-category/detail-category.component";
import { ToastrModule } from "ngx-toastr";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { DialogAddQuestionComponent } from './questions/dialog-add-question/dialog-add-question.component';
import { DialogInfoCourseComponent } from './courses/dialog-info-course/dialog-info-course.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        HomePageRoutingModule,
        MaterialModule,
        FlexLayoutModule,
        SharedModule, 
        CarouselModule.forRoot(),
        TabsModule.forRoot(),
        ButtonsModule.forRoot(),
        CollapseModule.forRoot(),
    ],
    declarations: [
        HomePage, 
        FooterComponent, 
        SidebarComponent,
        CoursesComponent,
        CategoryComponent,
        TestsComponent,
        QuestionsComponent,
        AccountsComponent,
        CourseCategoryItemComponent, 
        DialogAddCourseComponent, 
        DialogAddAccountComponent, 
        SettingAccountComponent, 
        DialogInfoAccountComponent, 
        MainUnregisteredComponent, 
        CoursesUnregisteredComponent, 
        AboutUsComponent,
        DashboardStudentComponent, 
        CoursesStudentComponent, 
        AllCourseStudentComponent, 
        CourseDetailComponent, 
        LearningCourseComponent,
        DialogAddCategoryComponent,
        DetailCategoryComponent,
        DialogAddQuestionComponent,
        DialogInfoCourseComponent
    ],
    entryComponents: [DialogAddCourseComponent],
})
export class HomePageModule {}