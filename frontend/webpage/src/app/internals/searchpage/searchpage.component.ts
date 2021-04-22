import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { SearchService } from 'src/app/_services/SearchService/search.service';
import { Question } from 'src/app/_shared/QuestionsModel';
import { User } from 'src/app/_shared/UserModel';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {

  constructor(private SearchService: SearchService, private route: ActivatedRoute) { }

  searchquery: String;

  showpage = new FormControl('question');

  questions: Array<Question> = [];
  profiles: Array<User> = [];

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(queryParams => {
      this.searchquery = queryParams.get("searchquery");
    });
    console.log(this.searchquery);
    this.questions = this.SearchService.retrievequestionsearchquery(this.searchquery);
    this.profiles = this.SearchService.retrieveprofilesearchquery(this.searchquery);

    console.log(this.profiles);
  }
}
