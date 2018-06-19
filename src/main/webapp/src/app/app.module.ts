import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RegistrationFormComponent} from "./components/registration-form/registration-form.component";

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AuthenticationService } from './services/authentication.service';
import { MainPageComponent } from './components/main-page/main-page.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import {TokenInterceptor} from "./services/token-interceptor";
import { NavbarComponent } from './components/navbar/navbar.component';
import { ArticleInfoComponent } from './components/article-info/article-info.component';
import {ArticleService} from "./services/article.service";
import {AddArticleFormComponent} from "./components/add-article-form/add-article-form.component";
import {FriendsComponent} from "./components/friends/friends.component";
import { ArticleComponent } from './components/article/article.component';
import { AboutComponent } from './components/about/about.component';
import { EditArticleFormComponent } from './components/edit-article-form/edit-article-form.component';
import { ArticleInfoListitemComponent } from './components/article-info-listitem/article-info-listitem.component';
import {TagPipe, TitlePipe} from "./components/main-page/main-page.component";
import { ShareArticleComponent } from './components/share-article/share-article.component';
import { AddSharedArticleComponent } from './components/add-shared-article/add-shared-article.component';
import {FriendService} from "./services/friend.service";
import { AddFriendComponent } from './components/add-friend/add-friend.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    MainPageComponent,
    StartPageComponent,
    RegistrationFormComponent,
    AppComponent,
    NavbarComponent,
    ArticleInfoComponent,
    AddArticleFormComponent,
    FriendsComponent,
    ArticleComponent,
    AboutComponent,
    EditArticleFormComponent,
    AboutComponent,
    ArticleInfoListitemComponent,
    TagPipe,
    TitlePipe,
    ShareArticleComponent,
    AddSharedArticleComponent,
    AddFriendComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    ArticleService,
    FriendService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
