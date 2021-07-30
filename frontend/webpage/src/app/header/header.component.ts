import { Component, OnInit } from '@angular/core';
import { SearchService } from '../_services/SearchService/search.service';
import { FormControl } from '@angular/forms';
import { SequenceEqualOperator } from 'rxjs/internal/operators/sequenceEqual';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private SearchService: SearchService) { }

  searchquery =  new FormControl('');

  ngOnInit(): void {
  }

  search(): void {
    this.SearchService.submitsearchquery(this.searchquery.value);
  }

}
