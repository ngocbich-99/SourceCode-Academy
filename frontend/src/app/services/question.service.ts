import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment as env } from 'src/environments/environment';
import { Question } from "../home/questions/question.model";

@Injectable({
    providedIn: 'root',
})
export class QuestionService {
    constructor(
        private http: HttpClient
    ) {}

    // get list question
    getListQuestion(): Observable<Question[]> {
        return this.http.get<Question[]>(env.backendBaseUrl + '/api/question/list');
    }

    // get question by id
    getQuestionById(id: number): Observable<Question> {
        return this.http.get<Question>(env.backendBaseUrl + `/api/question/${id}`);
    }

    // create question
    createQuestion(questionReq: Question): Observable<Question> {
        return this.http.post<Question>(env.backendBaseUrl + '/api/question/create', questionReq);
    }

    // update question
    updateQuestion(questionReq: Question): Observable<Question> {
        return this.http.put<Question>(env.backendBaseUrl + `/api/question/edit/${questionReq.id}`, questionReq);
    }

    // delete question
    deleteQuestion(id: number) {
        return this.http.delete(env.backendBaseUrl + `/api/question/delete/${id}`);
    }

}