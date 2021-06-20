import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../AuthService/auth-service.service';

@Injectable({
  providedIn: "root"
})
export class AuthGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router) {}

  canActivate() {
    return this.auth
      .isActivated()
      .toPromise()
      .then((data) => {
        return true;
      })
      .catch((err) => {
        console.log(err);
        this.auth.logout();
        return false;
      });
  }
}
