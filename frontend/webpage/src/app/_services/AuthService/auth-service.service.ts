import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) { }

  login(username:string, password: string) {
    // console.log(username);
    // console.log(password);

    if(username == 'kati' && password == 'testpwd') {
      let user = {
        'username': username
      };

      localStorage.setItem('current_user', JSON.stringify(user));
      console.log(JSON.parse(localStorage.getItem('current_user'))['username']);

      this.router.navigate(['/home'])
    } else {
      alert('Wrong password/username')
    }

  }
}
