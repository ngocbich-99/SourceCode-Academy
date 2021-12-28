import { environment as env } from 'src/environments/environment';

import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Account, ResponseAccount } from '../home/accounts/account.model';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { StatusToast, ToastServiceCodex } from './toast.service';
@Injectable({
    providedIn: 'root',
})
export class AccountService {
    constructor(
        private http: HttpClient,
        private toastService: ToastServiceCodex
        ) { }

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
    getAccountById(idAcc?: number): Observable<Account> {
        return this.http.get<{[key: string]: Account}>(env.backendBaseUrl + `/api/accounts/${idAcc}`)
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // add account
    createAccount(accountReq: Account): Observable<ResponseAccount> {
        return this.http.post<ResponseAccount>(env.backendBaseUrl + '/api/accounts', accountReq);
    }

    // update account
    updateAccount(accountReq: Account): Observable<ResponseAccount> {
        return this.http.put<ResponseAccount>(env.backendBaseUrl + '/api/accounts', accountReq);
    }

    // delete account by id
    deleteAccById(idAccount?: number): Observable<any> {
        return this.http.delete<any>(env.backendBaseUrl + `/api/accounts/${idAccount}`);
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