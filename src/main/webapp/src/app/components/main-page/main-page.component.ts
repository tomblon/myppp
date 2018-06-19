import {Component, OnInit, Pipe, PipeTransform} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {ArticleService} from "../../services/article.service";
import {Article as ArticleInfo} from "../../model/articleInfo";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss', './../../app.component.scss']
})
export class MainPageComponent implements OnInit {
  articles: ArticleInfo[];
  titleFilter: string = "";
  tagsFilter: string = "";
  tilesView: boolean = true;
  mail: string = "";


  constructor(private _service: AuthenticationService,
              private articleService: ArticleService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this._service.checkCredentials();
    this.getArticles();
    this.route.queryParams
      .subscribe(
        params => {
          console.log(params);
          this.mail = params.mail;
          console.log(this.mail);
        }
      )
  }

  getArticles() {
    this.articleService.getArticles().subscribe(articles => {
      this.articles = articles;
    });
  }

  logout() {
    this._service.logout();
  }

  setTilesView(tilesView: boolean) {
    this.tilesView = tilesView;
  }
}

@Pipe({
  name: 'tagsFilter',
  pure: false
})
export class TagPipe implements PipeTransform {
  transform(articles: ArticleInfo[], term: string) {
    if (!articles || !term) {
      return articles;
    }
    let filterTags = term.split('\n').filter(tag => tag !== '').map(tag => tag.toLocaleLowerCase());
    return articles.filter(article => this.arrayContainsArray(article.tags.map(tag => tag.name.toLocaleLowerCase()), filterTags));
  }

  arrayContainsArray(superset: string[], subset: string[]) {
    if (0 === subset.length) {
      return false;
    }
    return subset.every(function (value) {
      return (superset.filter(tag => tag.indexOf(value) !== -1).length !== 0);
    });

  }
}

@Pipe({
  name: 'titleFilter',
  pure: false
})
export class TitlePipe implements PipeTransform {
  transform(articles: ArticleInfo[], term: string) {
    if (!articles || !term) {
      return articles;
    }
    let termLower = term.toLocaleLowerCase();
    return articles.filter(article =>
      article.title.toLocaleLowerCase().indexOf(termLower) !== -1);
  }
}
