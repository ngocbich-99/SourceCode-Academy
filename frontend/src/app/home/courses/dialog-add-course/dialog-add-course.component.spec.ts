import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddCourseComponent } from './dialog-add-course.component';

describe('DialogAddCourseComponent', () => {
  let component: DialogAddCourseComponent;
  let fixture: ComponentFixture<DialogAddCourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogAddCourseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
