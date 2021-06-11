import { Injectable, Input } from '@angular/core';
import { empty } from 'rxjs';
import { User } from 'src/app/Models/UserModel';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor() { }

  retrieveprofile(input_username: String): User {

    let user = {
      username: input_username,
      email: 'mustachewj@gmail.com',
      phone: '0909093781',
      address: 'Bangkok, Thailand',
      title: 'Frontend Developer'
    }

    // retrieve user information from Webapi

    return new User(user.username, user.email, user.phone, user.address, user.title);
  }
}
