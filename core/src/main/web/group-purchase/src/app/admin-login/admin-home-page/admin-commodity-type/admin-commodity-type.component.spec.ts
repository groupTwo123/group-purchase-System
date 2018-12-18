import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCommodityTypeComponent } from './admin-commodity-type.component';

describe('AdminCommodityTypeComponent', () => {
  let component: AdminCommodityTypeComponent;
  let fixture: ComponentFixture<AdminCommodityTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCommodityTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCommodityTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
