import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../AuthService/auth-service.service';

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(public auth: AuthService, public router: Router) {}

    canActivate() {
       return this.auth.isActivated().toPromise().then((data) => {
           if(data["status"] == 200) {
               return true;
           } else {
               this.auth.logout();
               return false;
           }
       })
    }
}