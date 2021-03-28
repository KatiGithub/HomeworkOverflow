import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from '../AuthService/auth-service.service';

@Component({
  selector: 'app-login-signup',
  templateUrl: './login-signup.component.html',
  styleUrls: ['./login-signup.component.css'],
})
export class LoginSignupComponent implements OnInit {
  constructor(private authservice: AuthService) {}

  ngOnInit(): void {}

  credentials = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  submitsignin(): void {
    this.authservice.login(this.credentials.value['username'], this.credentials.value['password']);
  }
}
