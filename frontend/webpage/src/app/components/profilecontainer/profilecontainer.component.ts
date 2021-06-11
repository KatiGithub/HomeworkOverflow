import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../Models/UserModel';

@Component({
  selector: 'app-profilecontainer',
  templateUrl: './profilecontainer.component.html',
  styleUrls: ['./profilecontainer.component.css']
})
export class ProfilecontainerComponent implements OnInit {

  @Input('ProfileItem') profile: User;

  constructor() { }

  ngOnInit(): void {
  }

}
