import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class TagService {

  constructor(private http: HttpClient) {
  }

  getArticlesByTag(tag: String): Observable<any[]> {
    return this.http.get('/api/tags/' + tag);
  }

}
