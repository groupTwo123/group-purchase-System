import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayReturnPageComponent } from './pay-return-page.component';

describe('PayReturnPageComponent', () => {
  let component: PayReturnPageComponent;
  let fixture: ComponentFixture<PayReturnPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayReturnPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayReturnPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
