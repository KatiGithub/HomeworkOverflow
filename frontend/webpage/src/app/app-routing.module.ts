import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { HomePageComponent } from './internals/home-page/home-page.component';

const routes: Routes = [
  {
    path: '',
    component: LoginSignupComponent,
  },
  {
    path:'home',
    component: HomePageComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
