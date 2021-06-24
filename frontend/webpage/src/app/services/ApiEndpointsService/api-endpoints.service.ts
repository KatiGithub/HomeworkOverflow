import { Injectable } from '@angular/core';
import { constants } from 'src/app/Config/constants';
import { QueryStringParameters } from 'src/app/Shared/classes/query-string-parameters';
import { UrlBuilder } from 'src/app/Shared/classes/url-builder';
import { HttpService } from '../HttpService/http.service';
import { User } from 'src/app/Models/UserModel';
import { Answer } from 'src/app/Models/AnswerModel';

@Injectable({
  providedIn: 'root',
})
export class ApiEndpointsService {
  constructor(
    private constants: constants,
    private http: HttpService) {}

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

  public retrieveQuestionsById(questionid: Number) {
    // return this.createUrlWithPathVariables('api/question', [questionid]);
    console.log(this.createUrlWithPathVariables('api/question',[questionid]));
    return this.http.get(
      this.createUrlWithPathVariables('api/question',[questionid])
    )
  }

  public retrieveQuestionsforHome() {
    // return this.createUrl('api/question/home/');
    return this.http.get(
      this.createUrl("api/question/home/")
    );
  }

  public retrieveQuestionsAnsweredByUsers(userid: Number) {
 
    return this.http.get(
      this.createUrlWithQueryParameters('api/question',
      (qs: QueryStringParameters) => {
        qs.push('userid', userid.toString());
      })
    );
  }

  public retrieveAnswersByAnswerId(answerid: Number): string {
    return this.createUrlWithPathVariables('api/answer', [answerid]);
  }

  public retrieveAnswersByQuestionId(questionid: Number) {
    return this.http.get(
      this.createUrlWithQueryParameters(
        'api/answer/',
        (qs: QueryStringParameters) => {
          qs.push('questionid', questionid.toString());
        }
      )
    );
  }

  public submitQuestion() {
    return this.createUrl('addquestion');
  }

  public submitAnswer(answer: Answer) {
    
  }

  public upvoteAnswer(answerid: Number){
    return this.http.get(
      this.createUrlWithPathVariables('api/upvote', [answerid])
    );
  }

  public downvoteAnswer(answerid: Number) {
    return this.http.get(
      this.createUrlWithPathVariables('api/downvote', [answerid])
    );
  }

  public retrieveProfile(userid: Number) {
    // return this.createUrlWithPathVariables('user', [userid]);
    return this.http.get(
      this.createUrlWithPathVariables('profile/user', [userid]));
  }

  public signup(loginCredential: Object) {
    // return this.createUrl('auth/signup/');

    return this.http.post(
      this.createUrl('auth/signup/'),
      loginCredential
    )
  }

  public login(token: string, email: string) {

    let bodyString = JSON.stringify({
      "email": email,
      "token": token
    });

    console.log("bodyString");
    console.log(bodyString);

    return this.http.post(
      this.createUrl('auth/login/'),
      bodyString
    );
  }
}
