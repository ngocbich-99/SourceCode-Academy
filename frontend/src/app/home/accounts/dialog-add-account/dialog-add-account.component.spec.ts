import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddAccountComponent } from './dialog-add-account.component';

describe('DialogAddAccountComponent', () => {
  let component: DialogAddAccountComponent;
  let fixture: ComponentFixture<DialogAddAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogAddAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
