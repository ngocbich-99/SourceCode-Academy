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
import { AllCourseComponent } from "./courses/all-course/all-course.component";
import { CourseCategoryItemComponent } from "./courses/course-category-item/course-category-item.component";
import { CoursesComponent } from "./courses/courses.component";
import { DialogAddCourseComponent } from "./courses/dialog-add-course/dialog-add-course.component";
import { HomePageRoutingModule } from "./home-routing.module";
import { HomePage } from "./home.page";
import { QuestionsComponent } from "./questions/questions.component";
import { SettingAccountComponent } from "./setting-account/setting-account.component";
import { TestsComponent } from "./tests/tests.component";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        HomePageRoutingModule,
        MaterialModule,
        FlexLayoutModule,
        SharedModule, 
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
        AllCourseComponent, 
        CourseCategoryItemComponent, 
        DialogAddCourseComponent, 
        DialogAddAccountComponent, 
        SettingAccountComponent,
    ],
    entryComponents: [DialogAddCourseComponent]
})
export class HomePageModule {}