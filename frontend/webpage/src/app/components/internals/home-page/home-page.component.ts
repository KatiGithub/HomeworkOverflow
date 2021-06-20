import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Question } from 'src/app/Models/QuestionsModel';
import { QuestionhandlerService } from '../../../services/QuestionHandler/questionhandler.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(
      private questionhandler: QuestionhandlerService,
      private route: ActivatedRoute
    ) { }

  questions: Array<Question> = [];

  ngOnInit(): void {
    let observer = this.questionhandler.retrievequestions().subscribe((data: Array<any>) => {

      console.log(data);

      for (let element of data) {
        this.questions.push(Question.mapObjectToQuestion(element));
      }
    },
    error => {
      console.log("ERROR")
    });

    console.log(this.questions);
  }
}
