import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessPerComponent } from './business-per.component';

describe('BusinessPerComponent', () => {
  let component: BusinessPerComponent;
  let fixture: ComponentFixture<BusinessPerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BusinessPerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessPerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
