import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../AuthService/auth-service.service';

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(public auth: AuthService, public router: Router) {}

    canActivate(): boolean {
        if(this.auth.isActivated() == false) {
            this.router.navigate(['']);
            return false;
        } else {
            return true;
        }
    }
}