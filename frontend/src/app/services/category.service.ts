import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
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
        return this.http.get<Category[]>(env.backendBaseUrl + '/api/category/list');
    }

    // get category by id
    getCategoryById(id: number): Observable<Category> {
        return this.http.get<Category>(env.backendBaseUrl + `/api/category/${id}`);
    }

    // add category by id
    createCategory(categoryReq: Category): Observable<Category> {
        return this.http.post<Category>(env.backendBaseUrl + '/api/category/create', categoryReq);
    }

    // update category by id
    updateCategory(categoryReq: Category): Observable<Category> {
        return this.http.put<Category>(env.backendBaseUrl + `/api/category/edit/${categoryReq.idCategory?.toString()}`, categoryReq);
    }

    // delete category by id
    deleteCategoryById(id?: number) {
        return this.http.delete(env.backendBaseUrl + `/api/category/delete/${id}`, {responseType: 'text'});
    }

}