import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { QuestionhandlerService } from 'src/app/services/QuestionHandler/questionhandler.service';
import { Answer } from 'src/app/Models/AnswerModel';
import { Question } from 'src/app/Models/QuestionsModel';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-answer-page',
  templateUrl: './answer-page.component.html',
  styleUrls: ['./answer-page.component.css'],
})
export class AnswerPageComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private questionhandler: QuestionhandlerService
  ) {}

  question_id: Number;
  answers: Array<Answer> = [];
  question: Question;
  upvotepressed: Boolean = false;
  downvotepressed: Boolean = false;

  answer = new FormControl('');

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.question_id = params.question_id;
    });

    console.log(this.question_id);
    this.questionhandler.RetrieveQuestionbyID(this.question_id).subscribe((data) => {
      console.log(data);
      this.question = Question.mapObjectToQuestion(data);
      console.log(this.question);
    });

    this.questionhandler.RetrieveAnswersofQuestion(this.question_id).subscribe((data: Array<any>) => {
      console.log(data);
      for(let element of data) {
        this.answers.push(Answer.mapJsonToAnswer(element));
      }
    },
    error => {
      console.log(error);
    });

    
  }

  submitanswer(): void {
    console.log(this.answer.value);
    // this.answers.push(
    //   new Answer(
    //     Math.floor(Math.random() * 1000),
    //     false,
    //     false,
    //     'Mark',
    //     this.answer.value,
    //     new Date(),
    //     Math.floor(Math.random() * 1000),
    //     Math.floor(Math.random() * 60)
    //   )
    // );
    this.answer.disable();
    this.answer.reset();
  }

  upvotebuttonpressed(answer: Answer): void {
    console.log(answer.answer_id);

    this.questionhandler.upvoteanswer(answer.answer_id).subscribe((data: Number) => {
      answer.upvotes = data;
      if(answer.upvote) {
        answer.upvote = null;
      } else if(!answer.upvote) {
        answer.upvote = true;
      }
      console.log(answer);
    });
  }

  downvotebuttonpressed(answer: Answer): void {
    this.questionhandler.downvoteanswer(answer.answer_id).subscribe((data: Number) => {
      answer.upvotes = data;

      if(answer.upvote === null || answer.upvote == true) {
        answer.upvote = false;
      } else {
        answer.upvote = null;
      }

      console.log(answer);
    })
  }
}
