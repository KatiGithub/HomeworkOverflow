import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSignupComponent } from './components/login-signup/login-signup.component';
import { HomePageComponent } from './components/internals/home-page/home-page.component';
import { ProfilePageComponentComponent } from './components/internals/profile-page-component/profile-page-component.component';
import { AnswerPageComponent } from './components/internals/answer-page/answer-page.component';
import { AuthGuardService } from './services/AuthGuardService/AuthGuardService';
import { SearchpageComponent } from './components/internals/searchpage/searchpage.component';
import { AskQuestionPageComponent } from './components/internals/ask-question-page/ask-question-page.component';
import { SignupPageComponent } from './components/signup-page/signup-page.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginSignupComponent
  },
  {
    path:'home',
    component: HomePageComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'profile/:userid',
    component: ProfilePageComponentComponent,
    canActivate: [AuthGuardService]
  },
  {
    path:'question/:question_id',
    component: AnswerPageComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'search',
    component: SearchpageComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'askquestion',
    component: AskQuestionPageComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'signup',
    component: SignupPageComponent
  },
  {
    path: '**',
    redirectTo: '/login',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
