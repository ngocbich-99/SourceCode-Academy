import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonalInfoComponent } from './component/personal-info/personal-info.component';
import { MaterialModule } from '../material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DeleteDialogComponent } from './component/delete-dialog/delete-dialog.component';

@NgModule({
  declarations: [
    PersonalInfoComponent,
    DeleteDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule, 
    FlexLayoutModule
  ],
  exports: [
    PersonalInfoComponent,
    DeleteDialogComponent
  ]
})
export class SharedModule { }
