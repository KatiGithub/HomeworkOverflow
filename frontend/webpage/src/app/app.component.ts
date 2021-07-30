import { Component, enableProdMode } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterEvent } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'test';

  visibility: boolean = false;

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      if(this.router.url != '/login' && this.router.url != '/signup') {
        this.visibility = true;
      } else {
        this.visibility = false;
      }
    });
  }
}
