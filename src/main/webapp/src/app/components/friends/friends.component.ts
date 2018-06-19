import {Component, OnInit} from '@angular/core';
import {Friend} from "../../model/friend";
import {FriendService} from "../../services/friend.service";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss', './../../app.component.scss']
})
export class FriendsComponent implements OnInit {
  friends: Observable<Friend[]>;
  headers: string[];

  constructor(private friendService: FriendService,
              private router: Router) {
  }

  ngOnInit() {
    this.headers = this.friendService.getColumnsHeaders();
    this.friends = this.friendService.getFriends();
  }

  deleteFriendRequest(friend) {
    this.friendService.deleteFriend(friend.id).subscribe(
      () => {
        this.headers = this.friendService.getColumnsHeaders();
        this.friends = this.friendService.getFriends();
      });
  }

  navigateToNewFriend() {
    this.router.navigate(['/add_friend'])
  }
  shareToFriend(friend:Friend){
    this.router.navigate(['/my_articles'],{queryParams:{
      mail:friend.email
      }
    })
  }
}
