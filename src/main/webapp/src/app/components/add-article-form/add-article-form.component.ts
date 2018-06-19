import {ChangeDetectorRef, Component} from '@angular/core';
import {Article} from "../../model/article";
import {ArticleService} from "../../services/article.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Tag} from "../../model/tag";

@Component({
  selector: 'app-add-article-form',
  templateUrl: './add-article-form.component.html',
  styleUrls: ['./add-article-form.component.scss', './../../app.component.scss']
})

export class AddArticleFormComponent {
  article: Article = new Article();
  tagsAsString: string = "";
  tagToAdd: string = "";

  constructor(private articleService: ArticleService,
              private detectorRef: ChangeDetectorRef,
              private router: Router) {
  }

  addArticleRequest(f: NgForm) {
    let tags: Tag[] = this.tagsAsString === '' ? []  :
      this.parseToTags()
        .filter(s => s !== '')
        .map(name => new Tag(name));

    this.articleService.addArticle({
      title: f.value.title,
      url: f.value.url,
      comment: f.value.comment,
      tags: tags.map(tag => ({name: tag.name}))
    }).subscribe(() => {
      this.detectorRef.detectChanges();
      this.router.navigate(['/my_articles']);
    });
  }

  removeTag(tag: string) {
    var tags: string[] =  this.parseToTags();
    var updated: string = "";

    for (var i in tags) {
      if (!(tags[i] === tag)){
        if (updated.length > 0) updated  += "\n";
        updated = updated + tags[i];
      }
    }
    this.tagsAsString = updated;
  }

  addTag() {
    if (this.parseToTags().indexOf(this.tagToAdd) < 0) {
      this.tagsAsString = (this.tagsAsString.length > 0) ? this.tagsAsString + "\n" + this.tagToAdd : this.tagToAdd;
      this.tagToAdd = "";
    }
  }

  parseToTags(): string[] {
    return (this.tagsAsString.length > 0) ? this.tagsAsString.split("\n") : [];
  }

}
