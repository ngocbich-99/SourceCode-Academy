import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../home/accounts/account.model';
import {environment as env} from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginResponse, SignUpReq } from './auth.model';
import { User } from './user.model';
import { tap } from 'rxjs/operators';
import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn = false;
  currentUser = new BehaviorSubject<User>({}) ;

  constructor(
    private http: HttpClient
  ) { }

  isAuthenticated() {
    const promise = new Promise(
      (resolve, reject) => {
        setTimeout(() => {
          resolve(this.loggedIn);
        }, 100);
      }
    );
    return promise;
  }

  signUp(signupReq: SignUpReq): Observable<any> {
    return this.http.post<any>(env.backendBaseUrl + '/api/auth/register', signupReq);
  }

  login(email: string, pass: string): Observable<LoginResponse> {
    const loginReq = {
      username: email,
      password: pass
    }
    return this.http.post<LoginResponse>(env.backendBaseUrl + '/api/auth/login', loginReq)
    .pipe(
      tap(resData => {
        this.currentUser.next(resData.data);
      })
    );
  }
  logout() {
    this.loggedIn = false;
  }
}
