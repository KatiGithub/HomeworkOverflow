import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })

export class constants {
    public readonly API_URL:string = 'http://localhost:8080';
    public static ID_TOKEN:string = "";
}