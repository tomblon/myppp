import {Component, Input, OnInit} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-share-article',
  templateUrl: './share-article.component.html',
  styleUrls: ['./share-article.component.scss']
})
export class ShareArticleComponent implements OnInit {

  @Input() id: number;
  @Input() mail: string;
  userMail: string;

  constructor(private articleService: ArticleService) {
  }

  ngOnInit() {
    if (this.mail) {
      this.userMail = this.mail;
    }
  }

  share() {
    this.articleService.shareArticle(this.id, {"userMail": this.userMail})
      .subscribe();
  }

}
