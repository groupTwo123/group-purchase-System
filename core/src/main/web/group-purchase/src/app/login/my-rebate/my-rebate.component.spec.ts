/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MyRebateComponent } from './my-rebate.component';

describe('MyRebateComponent', () => {
  let component: MyRebateComponent;
  let fixture: ComponentFixture<MyRebateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyRebateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRebateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
