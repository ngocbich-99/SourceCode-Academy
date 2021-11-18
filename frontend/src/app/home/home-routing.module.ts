import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../auth/login/login.component";
import { SignUpComponent } from "../auth/sign-up/sign-up.component";
import { AccountsComponent } from "./accounts/accounts.component";
import { CategoryComponent } from "./category/category.component";
import { CourseCategoryItemComponent } from "./courses/course-category-item/course-category-item.component";
import { CoursesComponent } from "./courses/courses.component";
import { HomePage } from "./home.page";
import { QuestionsComponent } from "./questions/questions.component";
import { SettingAccountComponent } from "./setting-account/setting-account.component";
import { TestsComponent } from "./tests/tests.component";

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
            { path: 'accounts', component: AccountsComponent},
            { path: 'setting-account', component: SettingAccountComponent},
            {
                path: '',
                redirectTo: '/home/courses/all',
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