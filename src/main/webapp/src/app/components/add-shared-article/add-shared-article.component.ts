import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {Location} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-shared-article',
  templateUrl: './add-shared-article.component.html',
  styleUrls: ['./add-shared-article.component.scss']
})
export class AddSharedArticleComponent implements OnInit {
  private id: string;

  constructor(private articleService: ArticleService,
              private location: Location,
              private _router: Router) {
  }

  ngOnInit() {
    this.id = this.location.path().split("/")[2];
    this.articleService.getShared(this.id).subscribe(data => {
        this._router.navigate(['/my_articles'])
      },
      error => {
        console.log(error.message);
        this._router.navigate(['/my_articles'])
      }
    );
  }

}
