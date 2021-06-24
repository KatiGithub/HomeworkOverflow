import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AuthService } from 'src/app/services/AuthService/auth-service.service';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css']
})
export class SignupPageComponent implements OnInit {

  constructor(
    private auth: AuthService
  ) { }

  username = new FormControl('');
  clicked_submit = false;

  ngOnInit(): void {
  }

  logout(): void {
    this.auth.logout();
  }

  submitusername(): void {
    if(this.username.value != "") {
      this.clicked_submit = true;
      let error = this.auth.signUpUser(this.username.value);
    }
  }

}
