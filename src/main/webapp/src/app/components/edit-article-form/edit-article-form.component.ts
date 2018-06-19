import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {ArticleService} from "../../services/article.service";

@Component({
  selector: 'app-edit-article-form',
  templateUrl: './edit-article-form.component.html',
  styleUrls: ['./edit-article-form.component.scss']
})
export class EditArticleFormComponent implements OnInit {

  @Input() id: string = "";
  @Input() tagsAsString: string = "";
  tagToAdd: string = "";

  constructor(private articleService: ArticleService,
              private detectorRef: ChangeDetectorRef) {
  }

  ngOnInit() {
  }

  parseToTags(): string[] {
    return (this.tagsAsString && this.tagsAsString.length > 0) ? this.tagsAsString.split("\n") : [];

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
    this.editArticleTagsRequest();
  }

  addTag() {
    if (this.parseToTags().indexOf(this.tagToAdd) < 0) {
      this.tagsAsString = (this.tagsAsString.length > 0) ? this.tagsAsString + "\n" + this.tagToAdd : this.tagToAdd;
      this.tagToAdd = "";
      this.editArticleTagsRequest();
    }
  }

  editArticleTagsRequest() {
    this.articleService.editArticleTags(this.id, this.parseToTags()).subscribe(() => {
      this.detectorRef.detectChanges();
    });
  }
}

