import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProfileService } from 'src/app/services/ProfileService/profile.service';
import { QuestionhandlerService } from 'src/app/services/QuestionHandler/questionhandler.service';
import { Question } from 'src/app/Models/QuestionsModel';
import { User } from '../../../Models/UserModel';

@Component({
  selector: 'app-profile-page-component',
  templateUrl: './profile-page-component.component.html',
  styleUrls: ['./profile-page-component.component.css']
})
export class ProfilePageComponentComponent implements OnInit {

  constructor(private route: ActivatedRoute, private ProfileService: ProfileService, public questionhandler: QuestionhandlerService) { }

  userondisplay: User;
  userquestions: Array<Question> = [];

  asker_id: Number;

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.asker_id = params.userid;
    });

    this.ProfileService.retrieveprofile(this.asker_id).then((data) => {
      this.userondisplay = new User(
        data["firstname"],
        data["lastname"],
        data["userid"],
        data["email"],
        data["userlocation"],
        data["title"],
        data["githubhandle"],
        data["twitterhandle"],
        data["facebookhandle"]
      );

      Object.keys(this.userondisplay).forEach((item) => {
        if(this.userondisplay[item] == null) {
          this.userondisplay[item] = "N/A";
        }
      })

      console.log(this.userondisplay);
    })
    
    this.userquestions = this.questionhandler.RetrieveQuestionsAnsweredbyUser(this.asker_id);

    
  }
  
}
