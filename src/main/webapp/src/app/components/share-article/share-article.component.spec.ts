import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShareArticleComponentComponent } from './share-article-component.component';

describe('ShareArticleComponentComponent', () => {
  let component: ShareArticleComponentComponent;
  let fixture: ComponentFixture<ShareArticleComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShareArticleComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShareArticleComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
