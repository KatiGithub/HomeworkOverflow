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
    console.log(answer);
    console.log(answer.answerid);

    this.questionhandler.upvoteanswer(answer.answerid).subscribe((data: Number) => {
      answer.upvotes = data;
      if(answer.upvoteUserStatus) {
        answer.upvoteUserStatus = null;
      } else if(!answer.upvoteUserStatus) {
        answer.upvoteUserStatus = true;
      }
      console.log(answer);
    });
  }

  downvotebuttonpressed(answer: Answer): void {
    this.questionhandler.downvoteanswer(answer.answerid).subscribe((data: Number) => {
      answer.upvotes = data;

      if(answer.upvoteUserStatus === null || answer.upvoteUserStatus == true) {
        answer.upvoteUserStatus = false;
      } else {
        answer.upvoteUserStatus = null;
      }

      console.log(answer);
    })
  }
}
