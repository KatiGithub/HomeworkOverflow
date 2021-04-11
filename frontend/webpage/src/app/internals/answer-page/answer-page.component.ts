import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { QuestionhandlerService } from 'src/app/_services/QuestionHandler/questionhandler.service';
import { Answer } from 'src/app/_shared/AnswerModel';
import { Question } from 'src/app/_shared/QuestionsModel';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-answer-page',
  templateUrl: './answer-page.component.html',
  styleUrls: ['./answer-page.component.css']
})
export class AnswerPageComponent implements OnInit {

  constructor(private route: ActivatedRoute, private questionhandler: QuestionhandlerService) { }

  question_id: Number;
  answers: Array<Answer>
  question: Question;

  answer = new FormControl('');

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.question_id = params.question_id;
    });

    console.log(this.question_id);
    this.question = this.questionhandler.RetrieveQuestionbyID(this.question_id);
    this.answers = this.questionhandler.RetrieveAnswersofQuestion(this.question_id);
  }

  submitanswer(): void {
    console.log(this.answer.value);
    this.answers.push(new Answer('Mark', this.answer.value, new Date(), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)));
    this.answer.disable();
    this.answer.reset();
  }

}
