import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from '../../Models/QuestionsModel';

@Component({
  selector: 'app-questioncontainer',
  templateUrl: './questioncontainer.component.html',
  styleUrls: ['./questioncontainer.component.css']
})
export class QuestioncontainerComponent implements OnInit {

  @Input('QuestionItem') question: Question;

  constructor(private router: Router) { }

  ngOnInit(): void {
    console.log(this.question.tags);
  }

  onTagNavigate(tag: String) {
    let tagSearchQuery = 'tag:' + tag; 

    this.router.navigate(['search'], {
      queryParams: {searchquery: tagSearchQuery}
    });
  }

}
