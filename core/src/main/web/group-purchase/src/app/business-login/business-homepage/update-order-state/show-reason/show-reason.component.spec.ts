import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowReasonComponent } from './show-reason.component';

describe('ShowReasonComponent', () => {
  let component: ShowReasonComponent;
  let fixture: ComponentFixture<ShowReasonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowReasonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowReasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
