import { environment as env } from 'src/environments/environment';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Course, CourseRequest } from '../home/courses/course.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

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
        return this.http.get<{[key: string]: Course[]}>(env.backendBaseUrl + '/api/course/list')
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // get course by id
    getCourse(id?: number): Observable<Course> {
        return this.http.get<Course>(env.backendBaseUrl + `/api/course/${id}`);
    }

    // get info course

    // get course public

    // get course private

    // create course
    createCourse(courseReq: CourseRequest): Observable<Course> {
        return this.http.post<{[key: string]: Course}>(env.backendBaseUrl + '/api/course', courseReq)
        .pipe(
            map(resData => {
                return resData.data;
            })
        );
    }

    // edit course

    // delete course
    deleteCourse(id?: number) {
        return this.http.delete(env.backendBaseUrl + `/api/course/delete/${id}`);
    }

}