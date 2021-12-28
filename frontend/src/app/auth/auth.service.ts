import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../home/accounts/account.model';
import {environment as env} from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginResponse, SignUpReq } from './auth.model';
import { map, tap } from 'rxjs/operators';
import { UserInfo } from './user.model';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn = false;
  userInfoSubject = new BehaviorSubject<any>({});

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
        localStorage.setItem('userInfo', JSON.stringify(resData.data));
        this.userInfoSubject.next(resData.data);
      })
    );
  }
  logout() {
    localStorage.removeItem('userInfo');
    localStorage.removeItem('codex-token');
  }

  getUserInfo(): Observable<UserInfo> {
    return this.http.get<{[key: string]: UserInfo}>(env.backendBaseUrl + '/api/user')
    .pipe(
      map(resData => {
        localStorage.setItem('userInfo', JSON.stringify(resData.data));
        this.userInfoSubject.next(resData.data);
        return resData.data;
      })
    );
  }

  changeUserInfo(userInfo: UserInfo) {
    return this.http.post<{[key: string]: UserInfo}>(env.backendBaseUrl + '/api/user/change-user-info', 
    {
      fullName: userInfo.fullName,
      phone: userInfo.phone
    }).pipe(
      map(resData => {
        return resData.data;
      })
    )
  }

  changePassword(oldPass: string, newPass: string): Observable<any> {
    return this.http.post<any>(env.backendBaseUrl + '/api/user/change-password', {
      oldPassword: oldPass,
      newPassword: newPass,
      reNewPassword: newPass
    });
  }
}
