import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-profile-page-component',
  templateUrl: './profile-page-component.component.html',
  styleUrls: ['./profile-page-component.component.css']
})
export class ProfilePageComponentComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  asker_username: String;

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.asker_username = params.username;
    });

    console.log(this.asker_username);
  }

}
