import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseCategoryItemComponent } from './course-category-item.component';

describe('CourseCategoryItemComponent', () => {
  let component: CourseCategoryItemComponent;
  let fixture: ComponentFixture<CourseCategoryItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseCategoryItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseCategoryItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
