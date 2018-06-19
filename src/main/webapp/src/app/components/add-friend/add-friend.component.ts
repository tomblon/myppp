import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FriendService} from "../../services/friend.service";
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-friend',
  templateUrl: './add-friend.component.html',
  styleUrls: ['./add-friend.component.scss', './../../app.component.scss']
})
export class AddFriendComponent implements OnInit {

  constructor(private friendService: FriendService,
              private detectorRef: ChangeDetectorRef,
              private router: Router) { }

  ngOnInit() {
  }

  addFriendRequest(f: NgForm) {
    this.friendService.addFriend({
      name: f.value.name,
      email: f.value.email})
      .subscribe(() => {
        this.detectorRef.detectChanges();
        this.router.navigate(['/friends']);
      });
    }
}
