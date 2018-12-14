import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessCommodityListComponent } from './business-commodity-list.component';

describe('BusinessCommodityListComponent', () => {
  let component: BusinessCommodityListComponent;
  let fixture: ComponentFixture<BusinessCommodityListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BusinessCommodityListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessCommodityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
