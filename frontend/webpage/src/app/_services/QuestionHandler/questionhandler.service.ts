import { Injectable } from '@angular/core';
import { Answer } from 'src/app/_shared/AnswerModel';
import { Question } from '../../_shared/QuestionsModel';

@Injectable({
  providedIn: 'root'
})
export class QuestionhandlerService {

  asker_username_testlist = ['Mark', 'John', 'Mary', 'Thomas', 'Judas', 'Leviticus', 'Adam', 'Eve', 'Zuccheus', 'Samael'];
  testquestion = 'What is 0 to the power of 0?'

  retrievequestions(): Array<Question> {
    let ListQuestion: Array<Question> = [];

    for(let x = 0; x < 10; x++) {
      // console.log(this.ListQuestion);
      ListQuestion.push(new Question(x, this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60), ['math']));
      // this.ListQuestion[x] = new Question(this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)).QuestionToObject();
    }

    return ListQuestion;
  }

  RetrieveQuestionsAnsweredbyUser(username: String): Array<Question> {
    let ArrayofQuestion: Array<Question> = [];

    for(let x = 0; x < 10; x++) {
      ArrayofQuestion.push(new Question(x, this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60), ['math']));
    }

    return ArrayofQuestion;
  }

  RetrieveAnswersofQuestion(question_id: Number): Array<Answer> {
    let ArrayofAnswers: Array<Answer> = [];

    ArrayofAnswers.push(new Answer(Math.floor(Math.random() * 1000), true, false, this.asker_username_testlist[2], '<p>The answer is 1</p>', new Date(), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)));
    ArrayofAnswers.push(new Answer(Math.floor(Math.random() * 1000), false, true, this.asker_username_testlist[3], '<p>The answer is 1</p>', new Date(), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)));

    return ArrayofAnswers;
  }

  RetrieveQuestionbyID(question_id: Number): Question {

    return new Question(question_id, this.testquestion, this.asker_username_testlist[3], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60), ['math']);
  }

  upvoteanswer(answer_id: Number): void {
    console.log('Upvoted answer id: ' + answer_id);
  }

  downvoteanswer(answer_id: Number): void {
    console.log('Downvoted answer id: ' + answer_id);
  }
}
