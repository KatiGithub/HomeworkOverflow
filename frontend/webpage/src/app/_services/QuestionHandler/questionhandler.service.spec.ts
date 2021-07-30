import { TestBed } from '@angular/core/testing';

import { QuestionhandlerService } from './questionhandler.service';

describe('QuestionhandlerService', () => {
  let service: QuestionhandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionhandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
