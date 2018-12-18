import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCommodityListComponent } from './admin-commodity-list.component';

describe('AdminCommodityListComponent', () => {
  let component: AdminCommodityListComponent;
  let fixture: ComponentFixture<AdminCommodityListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCommodityListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCommodityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
