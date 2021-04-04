import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { HomePageComponent } from './internals/home-page/home-page.component';
import { ProfilePageComponentComponent } from './internals/profile-page-component/profile-page-component.component';

const routes: Routes = [
  {
    path: '',
    component: LoginSignupComponent,
  },
  {
    path:'home',
    component: HomePageComponent,
  },
  {
    path: 'profile/:username',
    component: ProfilePageComponentComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
