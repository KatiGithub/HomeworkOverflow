import { Injectable } from '@angular/core';
import { Question } from '../../_shared/QuestionsModel';

@Injectable({
  providedIn: 'root'
})
export class QuestionhandlerService {

  ListQuestion: Array<Question> = [];
  
  asker_username_testlist = ['Mark', 'John', 'Mary', 'Thomas', 'Judas', 'Leviticus', 'Adam', 'Eve', 'Zuccheus', 'Samael'];
  testquestion = 'What is 0 to the power of 0?'

  retrievequestions(): Array<Question> {

    for(let x = 0; x < 10; x++) {
      // console.log(this.ListQuestion);
      this.ListQuestion.push(new Question(this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)));
      // this.ListQuestion[x] = new Question(this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)).QuestionToObject();
    }

    return this.ListQuestion;
  }
}
