import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../home/accounts/account.model';
import {environment as env} from 'src/environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn = false;

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

  signUp(accountReq: Account): Observable<any> {
    return this.http.post<any>(env.backendBaseUrl + '/api/accounts', accountReq);
  }

  login() {
    this.loggedIn = true;
  }
  logout() {
    this.loggedIn = false;
  }
}
