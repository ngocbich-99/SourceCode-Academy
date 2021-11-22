import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonalInfoComponent } from './component/personal-info/personal-info.component';
import { MaterialModule } from '../material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DeleteDialogComponent } from './component/delete-dialog/delete-dialog.component';
import { TopbarUnregisteredComponent } from './component/topbar-unregistered/topbar-unregistered.component';
import { CourseItemStudentComponent } from './component/course-item-student/course-item-student.component';

@NgModule({
  declarations: [
    PersonalInfoComponent,
    DeleteDialogComponent,
    TopbarUnregisteredComponent,
    CourseItemStudentComponent
  ],
  imports: [
    CommonModule,
    MaterialModule, 
    FlexLayoutModule
  ],
  exports: [
    PersonalInfoComponent,
    DeleteDialogComponent,
    TopbarUnregisteredComponent,
    CourseItemStudentComponent
  ]
})
export class SharedModule { }
