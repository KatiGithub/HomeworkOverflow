import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { QuestionhandlerService } from 'src/app/_services/QuestionHandler/questionhandler.service';
import { Answer } from 'src/app/_shared/AnswerModel';
import { Question } from 'src/app/_shared/QuestionsModel';
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
  answers: Array<Answer>;
  question: Question;
  upvotepressed: Boolean = false;
  downvotepressed: Boolean = false;

  answer = new FormControl('');

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.question_id = params.question_id;
    });

    console.log(this.question_id);
    this.question = this.questionhandler.RetrieveQuestionbyID(this.question_id);
    this.answers = this.questionhandler.RetrieveAnswersofQuestion(
      this.question_id
    );
  }

  submitanswer(): void {
    console.log(this.answer.value);
    this.answers.push(
      new Answer(
        Math.floor(Math.random() * 1000),
        false,
        false,
        'Mark',
        this.answer.value,
        new Date(),
        Math.floor(Math.random() * 1000),
        Math.floor(Math.random() * 60)
      )
    );
    this.answer.disable();
    this.answer.reset();
  }

  upvotebuttonpressed(answer): void {
    console.log(answer.answer_id);
    if(this.answers[this.answers.indexOf(answer)].upvote == false && this.answers[this.answers.indexOf(answer)].downvote == true) {
      this.answers[this.answers.indexOf(answer)].upvote = true;
      this.answers[this.answers.indexOf(answer)].downvote = false;
      this.answers[this.answers.indexOf(answer)].upvotes = this.answers[this.answers.indexOf(answer)].upvotes + 2

      this.questionhandler.upvoteanswer(answer.answer_id);
    } else if(this.answers[this.answers.indexOf(answer)].upvote == true && this.answers[this.answers.indexOf(answer)].downvote == false) {
      this.answers[this.answers.indexOf(answer)].upvote = false;
      this.answers[this.answers.indexOf(answer)].downvote = false;
      this.answers[this.answers.indexOf(answer)].upvotes = this.answers[this.answers.indexOf(answer)].upvotes - 1

      this.questionhandler.upvoteanswer(answer.answer_id);
    } else if(this.answers[this.answers.indexOf(answer)].upvote == false && this.answers[this.answers.indexOf(answer)].downvote == false) {
      this.answers[this.answers.indexOf(answer)].upvote = true;
      this.answers[this.answers.indexOf(answer)].downvote = false;
      this.answers[this.answers.indexOf(answer)].upvotes = this.answers[this.answers.indexOf(answer)].upvotes + 1

      this.questionhandler.upvoteanswer(answer.answer_id);
    }
  }

  downvotebuttonpressed(answer): void {
    console.log(answer.answer_id);
    if(this.answers[this.answers.indexOf(answer)].downvote == false && this.answers[this.answers.indexOf(answer)].upvote == true) {
      this.answers[this.answers.indexOf(answer)].upvote = false;
      this.answers[this.answers.indexOf(answer)].downvote = true;
      this.answers[this.answers.indexOf(answer)].upvotes = this.answers[this.answers.indexOf(answer)].upvotes - 2

      this.questionhandler.downvoteanswer(answer.answer_id);
    } else if(this.answers[this.answers.indexOf(answer)].downvote == true && this.answers[this.answers.indexOf(answer)].upvote == false) {
      this.answers[this.answers.indexOf(answer)].downvote = false;
      this.answers[this.answers.indexOf(answer)].upvote = false;
      this.answers[this.answers.indexOf(answer)].upvotes = this.answers[this.answers.indexOf(answer)].upvotes + 1

      this.questionhandler.downvoteanswer(answer.answer_id);
    } else if(this.answers[this.answers.indexOf(answer)].downvote == false && this.answers[this.answers.indexOf(answer)].upvote == false) {
      this.answers[this.answers.indexOf(answer)].downvote = true;
      this.answers[this.answers.indexOf(answer)].upvotes = this.answers[this.answers.indexOf(answer)].upvotes - 1;

      this.questionhandler.upvoteanswer(answer.answer_id);
    }
  }
}
