import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { HomePageComponent } from './internals/home-page/home-page.component';
import { ProfilePageComponentComponent } from './internals/profile-page-component/profile-page-component.component';
import { AnswerPageComponent } from './internals/answer-page/answer-page.component';
import { AuthGuardService } from './_services/AuthGuardService/AuthGuardService';

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
    path: 'profile/:username',
    component: ProfilePageComponentComponent,
    canActivate: [AuthGuardService]
  },
  {
    path:'question/:question_id',
    component: AnswerPageComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/login',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
