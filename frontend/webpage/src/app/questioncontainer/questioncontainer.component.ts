import { Component, Input, OnInit } from '@angular/core';
import { Question } from '../_shared/QuestionsModel';

@Component({
  selector: 'app-questioncontainer',
  templateUrl: './questioncontainer.component.html',
  styleUrls: ['./questioncontainer.component.css']
})
export class QuestioncontainerComponent implements OnInit {

  @Input('QuestionItem') question: Question;

  constructor() { }

  ngOnInit(): void {
  }

}
