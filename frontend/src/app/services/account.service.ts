import { environment as env } from 'src/environments/environment';

import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Account } from '../home/accounts/account.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class AccountService {
    constructor(private http: HttpClient) { }

    // get list account
    getListAccount(): Observable<Account[]> {
        return this.http.get<Account[]>(env.backendBaseUrl + '/api/accounts');
    }

    // get account by id 
    getAccountById(idAcc: number): Observable<Account> {
        return this.http.get<Account>(env.backendBaseUrl + `/api/accounts/${idAcc}`)
    }

    // add account
    createAccount(accountReq: Account): Observable<Account> {
        return this.http.post<Account>(env.backendBaseUrl + '/api/accounts', accountReq);
    }

    // update account
    updateAccount(accountReq: Account): Observable<Account> {
        return this.http.put<Account>(env.backendBaseUrl + `/api/accounts/${accountReq.idAccount?.toString()}`, accountReq);
    }

    // delete account by id
    deleteAccById(idAccount?: number) {
        return this.http.delete(env.backendBaseUrl + `/api/accounts/${idAccount}`);
    }

}