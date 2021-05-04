import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProfileService } from 'src/app/_services/ProfileService/profile.service';
import { QuestionhandlerService } from 'src/app/_services/QuestionHandler/questionhandler.service';
import { Question } from 'src/app/_shared/QuestionsModel';
import { User } from '../../_shared/UserModel';

@Component({
  selector: 'app-profile-page-component',
  templateUrl: './profile-page-component.component.html',
  styleUrls: ['./profile-page-component.component.css']
})
export class ProfilePageComponentComponent implements OnInit {

  constructor(private route: ActivatedRoute, private ProfileService: ProfileService, public questionhandler: QuestionhandlerService) { }

  userondisplay: User = new User();
  userquestions: Array<Question> = [];

  asker_username: String;

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.asker_username = params.username;
    });

    console.log(this.asker_username);
    this.userondisplay = this.ProfileService.retrieveprofile(this.asker_username);
    this.userquestions = this.questionhandler.RetrieveQuestionsAnsweredbyUser(this.asker_username);
  }
  
}
