import { Injectable, Input } from '@angular/core';
import { empty, fromEventPattern } from 'rxjs';
import { User } from 'src/app/Models/UserModel';
import { HttpService } from '../HttpService/http.service';
import { ApiEndpointsService } from '../ApiEndpointsService/api-endpoints.service';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(
    private apiendpoints: ApiEndpointsService
  ) {}

  retrieveprofile(userid: Number): Promise<any>{
    let user: User;

    return this.apiendpoints.retrieveProfile(userid).toPromise();
    // retrieve user information from Webapi
  }
}
