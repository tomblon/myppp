import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {IArticle} from "../../model/article";
import {MainPageComponent} from "../main-page/main-page.component";

@Component({
  selector: 'app-article-info',
  templateUrl: './article-info.component.html',
  styleUrls: ['./article-info.component.scss']
})
export class ArticleInfoComponent implements OnInit {

  constructor(private articleService: ArticleService) {
  }

  @Input() data: IArticle;
  @Input() mail:string;
  @Output() articlesChange = new EventEmitter<string>();

  ngOnInit() {
  }

  deleteArticleRequest(article) {
    this.articleService.deleteArticle(article.id).subscribe(
      () => this.articlesChange.next("change")
    );
  }
}
