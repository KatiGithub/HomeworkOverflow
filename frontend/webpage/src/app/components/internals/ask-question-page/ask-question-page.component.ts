import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { timeout } from 'rxjs/operators';
import { QuestionhandlerService } from 'src/app/services/QuestionHandler/questionhandler.service';

@Component({
  selector: 'app-ask-question-page',
  templateUrl: './ask-question-page.component.html',
  styleUrls: ['./ask-question-page.component.css']
})
export class AskQuestionPageComponent implements OnInit {

  questiontitle = new FormControl('');
  question = new FormControl('');

  showsuccess = false;
  showerror = false;

  constructor(private questionhandler: QuestionhandlerService, private router: Router) { }

  ngOnInit(): void {
  }

  submitquestion() {
    console.log(this.questiontitle.value);
    console.log(this.question.value);
    
    this.question.disable();
    this.questiontitle.disable();

    if(this.questionhandler.submitaskquestion(this.question.value, this.questiontitle.value)) {
      this.showsuccess = true;

      setTimeout(() => this.router.navigate(['']), 6000);
    } else {  
      this.showerror = true;

      this.question.enable();
      this.questiontitle.enable();
    }
  }
}
