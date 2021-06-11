import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../services/SearchService/search.service';
import { FormControl } from '@angular/forms';
import { SequenceEqualOperator } from 'rxjs/internal/operators/sequenceEqual';
import { AuthService } from '../../services/AuthService/auth-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private SearchService: SearchService, private AuthService: AuthService) { }

  searchquery =  new FormControl('');

  ngOnInit(): void {
  }

  search(): void {
    this.SearchService.submitsearchquery(this.searchquery.value);
  }

  logout() {
    this.AuthService.logout();
  }
}
