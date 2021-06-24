import { HttpErrorResponse } from '@angular/common/http';
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
      .catch((err: HttpErrorResponse) => {
        console.log(err);

        if(err.status == 404) {
          this.router.navigate(['signup']);
          return false;
        }
        this.auth.logout();
        return false;
      });
  }
}
