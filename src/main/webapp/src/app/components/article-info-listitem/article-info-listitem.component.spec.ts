import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleInfoListitemComponent } from './article-info-listitem.component';

describe('ArticleInfoListitemComponent', () => {
  let component: ArticleInfoListitemComponent;
  let fixture: ComponentFixture<ArticleInfoListitemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleInfoListitemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleInfoListitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
