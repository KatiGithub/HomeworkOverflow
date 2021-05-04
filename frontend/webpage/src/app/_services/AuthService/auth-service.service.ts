import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/auth';
import firebase from 'firebase/app';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router, public afAuth: AngularFireAuth) {}

  login(email: String, token: String) {
    // console.log(username);
    // console.log(password);

    let userauthdata = {
      'email': email,
      'token': token
    };

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

  isActivated(): boolean {
    console.log(localStorage.getItem('current_user'));
    if (localStorage.getItem('current_user') != null) {
      return true;
    } else {
      return false;
    }
  }
}
