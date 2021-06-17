import { Injectable } from '@angular/core';
import { constants } from 'src/app/Config/constants';
import { QueryStringParameters } from 'src/app/Shared/classes/query-string-parameters';
import { UrlBuilder } from 'src/app/Shared/classes/url-builder';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/Models/UserModel';

@Injectable({
  providedIn: 'root',
})
export class ApiEndpointsService {
  constructor(
    private constants: constants,
    private http: HttpClient) {}

  // ----------------------------------------

  private createUrl(action: string): string {
    const urlBuilder: UrlBuilder = new UrlBuilder(
      this.constants.API_URL,
      action
    );

    return urlBuilder.toString();
  }

  private createUrlWithQueryParameters(
    action: string,
    queryStringHandler?: (queryStringParameters: QueryStringParameters) => void
  ): string {
    const urlBuilder: UrlBuilder = new UrlBuilder(
      this.constants.API_URL,
      action
    );

    if (queryStringHandler) {
      queryStringHandler(urlBuilder.querystring);
    }

    return urlBuilder.toString();
  }

  private createUrlWithPathVariables(
    action: string,
    pathVariables: any[] = []
  ): string {
    let encodedPathVariablesUrl: string = '';

    for (const pathVariable of pathVariables) {
      if (pathVariable !== null) {
        let tempstr = encodeURIComponent(pathVariable.toString());
        encodedPathVariablesUrl += '/' + tempstr;
      }
    }

    const urlBuilder: UrlBuilder = new UrlBuilder(
      this.constants.API_URL,
      `${action}${encodedPathVariablesUrl}`
    );

    return urlBuilder.toString();
  }

  // ----------------------------------------

  public retrieveQuestionsById(questionid: Number): string {
    return this.createUrlWithPathVariables('api/question', [questionid]);
  }

  public retrieveQuestionsforHome() {
    return this.createUrl('api/question/home');
  }

  public retrieveQuestionsAnsweredByUsers(userid: Number): string {
    return this.createUrlWithQueryParameters(
      'api/question',
      (qs: QueryStringParameters) => {
        qs.push('userid', userid.toString());
      }
    );
  }

  public retrieveAnswersByAnswerId(answerid: Number): string {
    return this.createUrlWithPathVariables('api/answer', [answerid]);
  }

  public retrieveAnswersByQuestionId(questionid: Number): string {
    return this.createUrlWithQueryParameters(
      'api/answer',
      (qs: QueryStringParameters) => {
        qs.push('questionid', questionid.toString());
      }
    );
  }

  public submitQuestion(): string {
    return this.createUrl('addquestion');
  }

  public upvoteQuestion(answerid: Number): string {
    return this.createUrlWithPathVariables('upvote', [answerid]);
  }

  public retrieveProfile(userid: Number) {
    // return this.createUrlWithPathVariables('user', [userid]);
    return this.http.get(
      this.createUrlWithPathVariables('profile/user', [userid]));
  }

  public signup(): string {
    return this.createUrl('auth/signup/');
  }

  public login(token: String, email: String){
    return this.http.post(
      this.createUrl('auth/login/'),
      {
        "token": token,
        "email": email
      }
    );
  }
}
