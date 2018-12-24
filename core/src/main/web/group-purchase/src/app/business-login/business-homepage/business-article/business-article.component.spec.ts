import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessArticleComponent } from './business-article.component';

describe('BusinessArticleComponent', () => {
  let component: BusinessArticleComponent;
  let fixture: ComponentFixture<BusinessArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BusinessArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
