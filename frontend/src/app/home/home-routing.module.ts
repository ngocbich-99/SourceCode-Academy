import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "../auth/auth.guard";
import { RoleGuard } from "../auth/role.guard";
import { AccountsComponent } from "./accounts/accounts.component";
import { CategoryComponent } from "./category/category.component";
import { CourseCategoryItemComponent } from "./courses/course-category-item/course-category-item.component";
import { CoursesComponent } from "./courses/courses.component";
import { HomePage } from "./home.page";
import { QuestionsComponent } from "./questions/questions.component";
import { SettingAccountComponent } from "./setting-account/setting-account.component";
import { AllCourseStudentComponent } from "./student-registered/all-course-student/all-course-student.component";
import { CoursesStudentComponent } from "./student-registered/courses-student/courses-student.component";
import { DashboardStudentComponent } from "./student-registered/dashboard-student/dashboard-student.component";
import { TestsComponent } from "./tests/tests.component";
import { AboutUsComponent } from "./Unregistered/about-us/about-us.component";
import { CoursesUnregisteredComponent } from "./Unregistered/courses-unregistered/courses-unregistered.component";
import { MainUnregisteredComponent } from "./Unregistered/main-unregistered/main-unregistered.component";

const routes: Routes = [
    {
        path: '',
        component: HomePage,
        children: [
            { 
                path: 'courses', component: CoursesComponent,
                children: [
                    { path: 'all', component: CourseCategoryItemComponent },
                    { path: ':id', component: CourseCategoryItemComponent },
                ]
            },
            { path: 'category', component: CategoryComponent},
            { path: 'tests', component: TestsComponent},
            { path: 'questions', component: QuestionsComponent},
            { path: 'accounts', component: AccountsComponent, canActivate: [RoleGuard]},
            { path: 'setting-account', component: SettingAccountComponent},

            { path: 'main-page-unregistered', component: MainUnregisteredComponent},
            { 
                path: 'courses-unregistered', 
                component: CoursesUnregisteredComponent, 
                children: [
                    { path: 'all', component: CourseCategoryItemComponent },
                    { path: ':id', component: CourseCategoryItemComponent },
                ]
            },
            { path: 'about-us', component: AboutUsComponent},

            { path: 'dashboard-student', component: DashboardStudentComponent},
            { path: 'courses-student', component: CoursesStudentComponent},
            { path: 'all-course-student', component: AllCourseStudentComponent},
            {
                path: '',
                redirectTo: '/home/main-page-unregistered',
                pathMatch: 'full'
            }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomePageRoutingModule { }