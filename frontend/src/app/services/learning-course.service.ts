import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment as env } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})

export class LearningCourseService {
    constructor(
        private http: HttpClient
    ) {}

    markLessonLearned(lessonId?: number) {
        return this.http.post(env.backendBaseUrl + '/api/lesson', {lessonId: lessonId});
    }
}