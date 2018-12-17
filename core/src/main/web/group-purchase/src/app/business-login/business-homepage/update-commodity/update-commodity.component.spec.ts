import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCommodityComponent } from './update-commodity.component';

describe('UpdateCommodityComponent', () => {
  let component: UpdateCommodityComponent;
  let fixture: ComponentFixture<UpdateCommodityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCommodityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCommodityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
