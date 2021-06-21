import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../AuthService/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private http: HttpClient) {}

  public get(url: string) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + JSON.parse(localStorage.getItem('current_user'))['token'],
    });

    console.log(headers);
    return this.http.get(url, { headers: headers, withCredentials: true });
  }

  public post(url: string, data: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('current_user')['token'],
    });
    return this.http.post(url, data, { headers: headers });
  }
}
