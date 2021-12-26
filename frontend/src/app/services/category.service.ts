import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment as env } from 'src/environments/environment';
import { Category } from "../home/category/category.model";

@Injectable({
    providedIn: 'root',
})
export class CategoryService {
    constructor(
        private http: HttpClient
    ) {}

    // get list category
    getListCategory(): Observable<Category[]> {
        return this.http.get<{[key: string]: Category[]}>(env.backendBaseUrl + '/api/category/list')
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // get category by id
    getCategoryById(id: number): Observable<Category> {
        return this.http.get<{[key: string]: Category}>(env.backendBaseUrl + `/api/category/${id}`)
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // add category by id
    createCategory(categoryReq: Category): Observable<Category> {
        return this.http.post<Category>(env.backendBaseUrl + '/api/category', categoryReq);
    }

    // update category by id
    updateCategory(categoryReq: Category): Observable<Category> {
        return this.http.put<Category>(env.backendBaseUrl + `/api/category/${categoryReq.id?.toString()}`, categoryReq);
    }

    // delete category by id
    deleteCategoryById(id?: number) {
        return this.http.delete(env.backendBaseUrl + `/api/category/${id}`, {responseType: 'text'});
    }

}