import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddressSelectComponent } from './form-address-select.component';

describe('FormAddressSelectComponent', () => {
  let component: FormAddressSelectComponent;
  let fixture: ComponentFixture<FormAddressSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAddressSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAddressSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
