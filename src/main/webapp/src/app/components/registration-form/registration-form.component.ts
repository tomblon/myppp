import {Component, OnInit} from "@angular/core";
import {AuthenticationService} from "../../services/authentication.service";
import {User} from "../../model/user";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss']
})
export class RegistrationFormComponent implements OnInit {
  model: {
    email: string;
    password: string;
  };

  constructor(private authenticationService: AuthenticationService,
              private _router: Router) {}

  ngOnInit() {
    this.model = {
      email: '',
      password: ''
    }
  }

  postRegister() {
    this.authenticationService
      .register(new User(this.model.email, this.model.password))
      .subscribe(() => {
        this._router.navigate(['/']);
    });
  }

  back(e) {
    e.preventDefault();
    this._router.navigate(['/']);
  }
}
