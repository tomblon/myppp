import {Article} from "./article";

export interface ITag {
  name: string;
  articles: Article[];
}

export class Tag implements ITag {
  public articles: Array<Article> = [];

  constructor(public name: string) {
  }

  public toJSON(): string {
    return JSON.stringify({
      name: this.name
    });
  }
}
