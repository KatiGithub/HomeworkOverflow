import { NullTemplateVisitor } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/Models/QuestionsModel';
import { User } from 'src/app/Models/UserModel';

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  constructor(private router: Router, private route: ActivatedRoute) {}

  question_searchresults: Array<Question> = [];
  profile_searchresults: Array<User> = [];

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

  submitsearchquery(searchquery) {
    this.router.navigate(['search'], {
      queryParams: { searchquery: searchquery },
    });
  }

  retrievequestionsearchquery(searchquery) {
    this.question_searchresults = [];
    for (let x = 0; x < 10; x++) {
      this.question_searchresults.push(
        new Question(
          x,
          this.testquestion,
          this.asker_username_testlist[x],
          Math.floor(Math.random() * 1000),
          Math.floor(Math.random() * 60),
          ['math'],
          new Date()
        )
      );
    }

    return this.question_searchresults;
  }

  retrieveprofilesearchquery(searchquery) {
    // let user = {
    //   email: 'mustachewj@gmail.com',
    //   phone: '0909093781',
    //   address: 'Bangkok, Thailand',
    //   title: 'Frontend Developer',
    // };

    // // retrieve user information from Webapi
    // this.profile_searchresults = [];
    // for (let x = 0; x < this.asker_username_testlist.length; x++) {
    //   this.profile_searchresults.push(
    //     new User(
    //       x,
    //       this.asker_username_testlist[x],
    //       user.email,
    //       user.phone,
    //       user.address,
    //       user.title
    //     )
    //   );
    // }

    return null;
  }
}
