import {Tag} from "./tag";

export interface IArticle {
  title: string;
  url: string;
  comment: string;
  text: string;
  tags: Tag[];
}


export class Article implements IArticle {
  public title: string = '';
  public url: string = '';
  public comment: string = '';
  public text: string = '';
  public tags: Tag[] = [];


  constructor() {}

  public toJSON(): string {
    return JSON.stringify({
      title: this.title,
      url: this.url,
      comment: this.comment,
      text: this.text,
      tags: this.tags
    });
  }
}
