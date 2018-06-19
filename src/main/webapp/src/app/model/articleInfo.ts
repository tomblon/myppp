import {Tag} from "./tag";

export class Article {
  public id: number;
  public title: string = '';
  public tags: Tag[] = [];

  constructor() {
  }

}
