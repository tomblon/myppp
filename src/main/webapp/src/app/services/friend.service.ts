import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Friend} from "../model/friend";

@Injectable()
export class FriendService {

  constructor(private http: HttpClient) { }

  getFriends(): Observable<Friend[]>{
    return this.http.get('/api/friends');
  }

  addFriend(friend):Observable<Friend> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = {
      headers: headers,
    };

    return this.http.post('/api/friends', friend, options);
  }

  getColumnsHeaders(): string[]{
    return ["id", "name", "email"]};

  deleteFriend(id): Observable<Friend> {
    return this.http.delete(`/api/friends/${id}`);
  }
}
