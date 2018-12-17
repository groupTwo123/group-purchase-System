import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateOrderStateComponent } from './update-order-state.component';

describe('UpdateOrderStateComponent', () => {
  let component: UpdateOrderStateComponent;
  let fixture: ComponentFixture<UpdateOrderStateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateOrderStateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateOrderStateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
