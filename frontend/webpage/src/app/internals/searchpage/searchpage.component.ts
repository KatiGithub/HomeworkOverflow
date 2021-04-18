import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { SearchService } from 'src/app/_services/SearchService/search.service';
import { Question } from 'src/app/_shared/QuestionsModel';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {

  constructor(private SearchService: SearchService, private route: ActivatedRoute) { }

  questions: Array<Question> = [];

  ngOnInit(): void {
    let searchquery = this.route.params.subscribe((params: Params) => {
      return params.searchquery;
    });
    this.questions = this.SearchService.retrievesearchquery(searchquery);
  }
}
