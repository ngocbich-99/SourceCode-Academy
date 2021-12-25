import { environment as env } from 'src/environments/environment';

import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Account } from '../home/accounts/account.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Injectable({
    providedIn: 'root',
})
export class AccountService {
    constructor(private http: HttpClient) { }

    // get list account
    getListAccount(): Observable<Account[]> {
        return this.http.get<{[key: string]: Account[]}>(env.backendBaseUrl + '/api/accounts')
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // get account by id 
    getAccountById(idAcc: number): Observable<Account> {
        return this.http.get<{[key: string]: Account}>(env.backendBaseUrl + `/api/accounts/${idAcc}`)
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // add account
    createAccount(accountReq: Account): Observable<Account> {
        return this.http.post<Account>(env.backendBaseUrl + '/api/accounts', accountReq);
    }

    // update account
    updateAccount(accountReq: Account): Observable<Account> {
        return this.http.put<Account>(env.backendBaseUrl + `/api/accounts/${accountReq.id?.toString()}`, accountReq);
    }

    // delete account by id
    deleteAccById(idAccount?: number) {
        return this.http.delete(env.backendBaseUrl + `/api/accounts/${idAccount}`, {responseType: 'text'});
    }

    // get account activate
    getAccountActivate(): Observable<Account[]> {
        return this.http.get<{[key: string]: Account[]}>(env.backendBaseUrl + '/api/accounts/activate')
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // get account locked
    getAccountLock(): Observable<Account[]> {
        return this.http.get<{[key: string]: Account[]}>(env.backendBaseUrl + '/api/accounts/lock')
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

}