import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from "./components/login-form/login-form.component";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {StartPageComponent} from "./components/start-page/start-page.component";
import {RegistrationFormComponent} from "./components/registration-form/registration-form.component";
import {FriendsComponent} from "./components/friends/friends.component";
import {ArticleComponent} from "./components/article/article.component";
import {AddArticleFormComponent} from "./components/add-article-form/add-article-form.component";
import {AboutComponent} from "./components/about/about.component";
import {AddSharedArticleComponent} from "./components/add-shared-article/add-shared-article.component";
import {AddFriendComponent} from "./components/add-friend/add-friend.component";

const routes: Routes = [
  {path: '', component: StartPageComponent},
  {path: 'my_articles', component: MainPageComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'registration', component: RegistrationFormComponent},
  {path: 'friends', component: FriendsComponent},
  {path: 'article/:id', component: ArticleComponent},
  {path: 'add', component: AddArticleFormComponent},
  {path: 'about', component: AboutComponent},
  {path: 'share_link/:id', component: AddSharedArticleComponent},
  {path: 'add_friend', component: AddFriendComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    {
      useHash: true
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
