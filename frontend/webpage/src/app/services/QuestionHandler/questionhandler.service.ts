
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';
import { Answer } from 'src/app/Models/AnswerModel';
import { User } from 'src/app/Models/UserModel';
import { Question } from '../../Models/QuestionsModel';
import { ApiEndpointsService } from '../ApiEndpointsService/api-endpoints.service';

@Injectable({
  providedIn: 'root',
})
export class QuestionhandlerService {
  constructor(private apiendpointsservice: ApiEndpointsService) {}

  asker_username_testlist = [
    'Mark',
    'John',
    'Mary',
    'Thomas',
    'Judas',
    'Leviticus',
    'Adam',
    'Eve',
    'Zuccheus',
    'Samael',
  ];
  testquestion = 'What is 0 to the power of 0?';

  retrievequestions(): Observable<Object> {
    return this.apiendpointsservice
      .retrieveQuestionsforHome();
  }

  RetrieveQuestionsAnsweredbyUser(userid: Number): Array<Question> {
    // let ArrayofQuestion: Array<Question> = [];

    // for(let x = 0; x < 10; x++) {
    //   ArrayofQuestion.push(new Question(x, this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60), ['math', 'science', 'arithmetics'], new Date()));
    // }

    return null;
  }

  RetrieveAnswersofQuestion(question_id: Number): Observable<Object> {
    return this.apiendpointsservice.retrieveAnswersByQuestionId(question_id);

  }

  RetrieveQuestionbyID(question_id: Number) {
    return this.apiendpointsservice.retrieveQuestionsById(question_id);
  }

  submitaskquestion(question: String, questiontitle: String) {
    // console.log("Question Asked!")

    return true;
  }

  upvoteanswer(answer_id: Number) {
    console.log('Upvoted answer id: ' + answer_id);

    return this.apiendpointsservice.upvoteAnswer(answer_id);
  }

  downvoteanswer(answer_id: Number) {
    console.log('Downvoted answer id: ' + answer_id);
    return this.apiendpointsservice.downvoteAnswer(answer_id);
  }
}
