import { environment as env } from 'src/environments/environment';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Course, CourseRequest, CourseResponse } from '../home/courses/course.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class CourseService {
    constructor(
        private http: HttpClient
    ) {
        
    }
    // get list category
    
    // get list course
    getListCourse(): Observable<Course[]> {
        return this.http.get<Course[]>(env.backendBaseUrl + '/api/course/list');
    }

    // get list course by id

    // get info course

    // get course public

    // get course private

    // create course
    createCourse(courseReq: CourseRequest): Observable<CourseResponse> {
        return this.http.post<CourseResponse>(env.backendBaseUrl + '/api/course', courseReq);
    }

    // edit course

    // delete course

}