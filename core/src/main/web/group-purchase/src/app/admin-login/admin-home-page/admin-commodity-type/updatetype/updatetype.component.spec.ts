import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatetypeComponent } from './updatetype.component';

describe('UpdatetypeComponent', () => {
  let component: UpdatetypeComponent;
  let fixture: ComponentFixture<UpdatetypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatetypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatetypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
