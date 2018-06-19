import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {Location} from "@angular/common";
import {IArticle} from "../../model/article";
import {ITag} from "../../model/tag";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ArticleComponent implements OnInit {

  id: string;
  article: IArticle;
  tags: ITag[];
  tagNames: string;
  private mail: string;

  constructor(private articleService: ArticleService,
              private location: Location,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.id = this.location.path().split("/")[2].split("?")[0];
    this.articleService.getArticle(this.id).subscribe(
      article => {
        this.article = article;
        this.tags = article.tags;
        this.tagNames = article.tags.map(tag => tag.name).join("\n");
      });
    this.route.queryParams.filter(
      params => params.mail
    )
      .subscribe(params => {
        this.mail = params.mail
        this.router.navigate([`article/${this.id}`])

      })
    if (this.mail==="undefined") {
      this.mail = ""
    }
  }

}
