import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSharedArticleComponent } from './add-shared-article.component';

describe('AddSharedArticleComponent', () => {
  let component: AddSharedArticleComponent;
  let fixture: ComponentFixture<AddSharedArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSharedArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSharedArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
