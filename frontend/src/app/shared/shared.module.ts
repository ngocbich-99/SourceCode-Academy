import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonalInfoComponent } from './component/personal-info/personal-info.component';
import { MaterialModule } from '../material.module';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
    PersonalInfoComponent
  ],
  imports: [
    CommonModule,
    MaterialModule, 
    FlexLayoutModule
  ],
  exports: [
    PersonalInfoComponent
  ]
})
export class SharedModule { }
