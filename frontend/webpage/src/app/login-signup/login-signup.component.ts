import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from '../_services/AuthService/auth-service.service';
import {
  trigger,
  state,
  style,
  animate,
  transition,
} from '@angular/animations';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-signup',
  templateUrl: './login-signup.component.html',
  styleUrls: ['./login-signup.component.css'],
})
export class LoginSignupComponent implements OnInit {
  constructor(private authservice: AuthService, private router: Router) {}

  ngOnInit(): void {
    if(this.authservice.isActivated()) {
      this.router.navigate(['/home'])
    }
  }

  credentials = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  submitsignin(): void {
    this.authservice.login(
      this.credentials.value['username'],
      this.credentials.value['password']
    );
  }
}