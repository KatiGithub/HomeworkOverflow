import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../HttpService/http.service'; 
import { ApiEndpointsService } from '../ApiEndpointsService/api-endpoints.service';
import { AngularFireAuth } from '@angular/fire/auth';
import firebase from 'firebase/app';
import { HttpErrorResponse } from '@angular/common/http';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private router: Router,
    public afAuth: AngularFireAuth,
    private apiendpointsservice: ApiEndpointsService) {}

  login(email: String, token: String) {
    // console.log(username);
    // console.log(password);

    let userauthdata = {
      'email': email,
      'token': token
    };

    console.log("current user:");
    console.log(userauthdata);

    localStorage.setItem('current_user', JSON.stringify(userauthdata));

    this.router.navigate(['home']);
  }

  doGoogleLogin() {
    return new Promise<any>((resolve, reject) => {
      let provider = new firebase.auth.GoogleAuthProvider();
      provider.addScope('email');
      provider.addScope('profile');
      provider.addScope('openid');
      this.afAuth.signInWithPopup(provider).then(
        (res) => {
          console.log(res);
          firebase.auth().onAuthStateChanged((user) => {
            if(user) {
              user.getIdToken().then((token) => {
                this.login(user.email, token)
              })
            } else {
              return;
            }
          });
          resolve(res);
        },
        (err) => {
          console.log('Error:');
          console.log(err.code);
          reject(err);
        }
      );
    }).catch((error) => {
      if(error.code == 'auth/account-exists-with-different-credential') {
        let pendingCredentials = error.credential;
        let email = error.email;
        console.log(pendingCredentials);
        console.log(email);
        this.afAuth.fetchSignInMethodsForEmail(email).then((methods) => {
          if (methods[0] == 'facebook.com') {
            let provider = new firebase.auth.FacebookAuthProvider();
            this.afAuth.signInWithPopup(provider).then((res) => {
                if(res.user.email == email) {
                  res.user.linkWithCredential(pendingCredentials).then(function(usercred) {
                    firebase.auth().onAuthStateChanged((user) => {
                      if(user) {
                        user.getIdToken().then((token) => {
                          this.login(user.email, token)
                        })
                      } else {
                        return;
                      }
                    });
                    console.log("Connected facebook and google");
                  })
                }
            });
          }
        });
      }
    })
  }

  doFacebookLogin() {
    return new Promise<any>((resolve, reject) => {
      let provider = new firebase.auth.FacebookAuthProvider();
      this.afAuth.signInWithPopup(provider).then(
        (res) => {
          console.log(res);
          firebase.auth().onAuthStateChanged((user) => {
            if(user) {
              user.getIdToken().then((token) => {
                this.login(user.email, token)
              })
            } else {
              return;
            }
          });
          resolve(res);

          this.router.navigate
        },
        (err) => {
          // console.log(err);
          reject(err);
        }
      );
    }).catch((error) => {
      if (error.code == 'auth/account-exists-with-different-credential') {
        let pendingCredentials = error.credential;
        let email = error.email;
        console.log(pendingCredentials);
        console.log(email);
        this.afAuth.fetchSignInMethodsForEmail(email).then((methods) => {
          if (methods[0] == 'google.com') {
            let provider = new firebase.auth.GoogleAuthProvider();
            this.afAuth.signInWithPopup(provider).then((res) => {
                if(res.user.email == email) {
                  res.user.linkWithCredential(pendingCredentials).then(function(usercred) {
                    firebase.auth().onAuthStateChanged((user) => {
                      if(user) {
                        user.getIdToken().then((token) => {
                          this.login(user.email, token)
                        })
                      } else {
                        return;
                      }
                    });
                    console.log("Connected facebook and google");
                  })
                }
            });
          }
        });
      }
    });
  }

  logout(): void {
    localStorage.removeItem('current_user');

    this.router.navigate(['']);
  }

  isActivated() {

    let credentials = localStorage.getItem('current_user');
    
    credentials = JSON.parse(credentials);
    
    if(credentials == null) {
      this.logout();
    } 

    return this.apiendpointsservice.login(
      credentials["token"],
      credentials["email"]
    );

  }

  getCurrentUserToken(): string {

    return localStorage.getItem('current_user')["token"];
  }

  signUpUser(username: String) {

    let credentials = JSON.parse(localStorage.getItem('current_user'));

    this.apiendpointsservice.signup({
      'username': username,
      'email': credentials['email'],
      'token': credentials['token']
    }).subscribe((data) => {
      this.router.navigate[''];
    }, (err: HttpErrorResponse) => {
      console.log(err);
      return err;
    });
  }
}
