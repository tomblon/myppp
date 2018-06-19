import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {IArticle} from "../model/article";
import {Article as ArticleInfo} from "../model/articleInfo";

@Injectable()
export class ArticleService {

  constructor(private http: HttpClient) {
  }

  getArticles(): Observable<ArticleInfo[]> {
    return this.http.get('/api/articles');
  }

  addArticle(article):Observable<IArticle> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = {
      headers: headers,
    };

    return this.http.post('/api/articles', article, options);
  }

  getArticle(id): Observable<IArticle> {
    return this.http.get(`/api/articles/${id}`);
  }

  editArticleTags(id: string, tags: Array<string>):Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = {
      headers: headers,
    };

    return this.http.post(`/api/articles/${id}`, tags, options);
  }


  deleteArticle(id): Observable<IArticle> {
    return this.http.delete(`/api/articles/${id}`);
  }

  shareArticle(id,body):Observable<object>{
    return this.http.post(`/api/articles/${id}/share`,body);
  }

  getShared(id):Observable<object>{
    return this.http.get(`/api/articles/share/${id}`);
  }

}
