import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPicComponent } from './admin-pic.component';

describe('AdminPicComponent', () => {
  let component: AdminPicComponent;
  let fixture: ComponentFixture<AdminPicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
