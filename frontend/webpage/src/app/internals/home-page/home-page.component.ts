import { Component, OnInit } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { Question } from 'src/app/_shared/QuestionsModel';
import { QuestionhandlerService } from '../../_services/QuestionHandler/questionhandler.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private questionhandler: QuestionhandlerService) { }

  questions: Array<Question> = [];

  ngOnInit(): void {
    this.questions = this.questionhandler.retrievequestions();
    console.log(this.questions);
  }

  

}
