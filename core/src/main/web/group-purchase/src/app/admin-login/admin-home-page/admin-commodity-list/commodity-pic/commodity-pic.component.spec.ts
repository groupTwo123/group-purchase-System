import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommodityPicComponent } from './commodity-pic.component';

describe('CommodityPicComponent', () => {
  let component: CommodityPicComponent;
  let fixture: ComponentFixture<CommodityPicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommodityPicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommodityPicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
