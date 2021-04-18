import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/_shared/QuestionsModel';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private router: Router, private route: ActivatedRoute) { }

  question_searchresults: Array<Question> = [];

  asker_username_testlist = ['Mark', 'John', 'Mary', 'Thomas', 'Judas', 'Leviticus', 'Adam', 'Eve', 'Zuccheus', 'Samael'];
  testquestion = 'What is 0 to the power of 0?';

  submitsearchquery(searchquery) {
    this.router.navigate(['search'], {queryParams: {'searchquery': searchquery}});
  }  

  retrievesearchquery(searchquery) {
    for(let x = 0; x < 10; x++) {
      this.question_searchresults.push(new Question(x, this.testquestion, this.asker_username_testlist[x], Math.floor(Math.random() * 1000), Math.floor(Math.random() * 60)));
    }

    return this.question_searchresults;
  }
}
