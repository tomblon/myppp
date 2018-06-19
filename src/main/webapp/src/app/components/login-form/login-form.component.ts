import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  model: {
    userMail: string;
    password: string;
  };

  constructor(private _service: AuthenticationService,
              private _router: Router) {
  }

  ngOnInit() {
    this.model = {
      userMail: '',
      password: ''
    }
  }

  postLogin() {
    console.log(this.model);
    this._service.login(this.model.userMail, this.model.password)
      .catch((err: any, caught: Observable<Response>) => {
        return Observable.throw(err);
      })
      .subscribe(() => {
        this._router.navigate(['/my_articles']);
      });

  }

  back(e) {
    e.preventDefault();
    this._router.navigate(['/']);
  }

}
