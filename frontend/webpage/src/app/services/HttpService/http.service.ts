import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const headers = new HttpHeaders({
  'Content-Type': 'application/json',
  Authorization: 'Bearer ' + JSON.parse(localStorage.getItem('current_user'))['token'],
});

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private http: HttpClient) {}

  public get(url: string) {
    console.log('Headers:');
    console.log(headers);
    return this.http.get(url, { headers: headers, withCredentials: true });
  }

  public post(url: string, data: any) {
    return this.http.post(url, data, { headers: headers });
  }
}
