import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment as env } from 'src/environments/environment';

import { Course, CourseRequest, ResCourseApi, ResPaginatorCourses } from '../home/courses/course.model';

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
        return this.http.get<{ [key: string]: Course[] }>(env.backendBaseUrl + '/api/course/list')
            .pipe(
                map(resData => {
                    return resData.data;
                })
            );
    }

    // get course by id
    getCourse(id?: number): Observable<Course> {
        return this.http.get<{ [key: string]: Course }>(env.backendBaseUrl + `/api/course/${id}`)
            .pipe(
                map(resData => {
                    return resData.data;
                })
            );
    }
    // get course public
    getCoursesPublic(): Observable<ResCourseApi> {
        return this.http.get<ResCourseApi>(env.backendBaseUrl + '/api/course/public');
    }

    // get course private
    getCoursesPrivate(): Observable<ResCourseApi> {
        return this.http.get<ResCourseApi>(env.backendBaseUrl + '/api/course/private');
    }

    // get list course dang hoc cua current user
    getCoursesLearning(
        pageSize: number,
        page: number,
        textSearch: string,
        sortProperty: string,
        sortOrder: string
    ): Observable<ResPaginatorCourses> {
        if (!!textSearch) {
            const params = new HttpParams()
                .set('pageSize', pageSize.toString())
                .set('page', page.toString())
                .set('textSearch', textSearch)
                .set('sortProperty', sortProperty)
                .set('sortOrder', sortOrder)
            return this.http.get<ResPaginatorCourses>(env.backendBaseUrl + '/api/course/current-user', { params })
        } else {
            const params = new HttpParams()
                .set('pageSize', pageSize.toString())
                .set('page', page.toString())
                .set('sortProperty', sortProperty)
                .set('sortOrder', sortOrder)
            return this.http.get<ResPaginatorCourses>(env.backendBaseUrl + '/api/course/current-user', { params })
        }
    }

    // get list course da hoan thanh cua current user
    getCoursesLearned(
        pageSize: number,
        page: number,
        textSearch: string,
        sortProperty: string,
        sortOrder: string
    ) {
        if (!!textSearch) {
            const params = new HttpParams()
                .set('pageSize', pageSize.toString())
                .set('page', page.toString())
                .set('textSearch', textSearch)
                .set('sortProperty', sortProperty)
                .set('sortOrder', sortOrder)
            return this.http.get<ResPaginatorCourses>(env.backendBaseUrl + '/api/course/current-user', { params })
        } else {
            const params = new HttpParams()
                .set('pageSize', pageSize.toString())
                .set('page', page.toString())
                .set('sortProperty', sortProperty)
                .set('sortOrder', sortOrder)
            return this.http.get<ResPaginatorCourses>(env.backendBaseUrl + '/api/course/current-user', { params })
        }
    }
    // create course
    createCourse(courseReq: CourseRequest): Observable<Course> {
        return this.http.post<{ [key: string]: Course }>(env.backendBaseUrl + '/api/course', courseReq)
            .pipe(
                map(resData => {
                    return resData.data;
                })
            );
    }

    // update course
    updateCourse(courseReq: CourseRequest): Observable<Course> {
        return this.http.put<{ [key: string]: Course }>(env.backendBaseUrl + '/api/course', courseReq)
            .pipe(
                map(resData => {
                    return resData.data;
                })
            );
    }


    // delete course
    deleteCourse(id?: number) {
        return this.http.delete(env.backendBaseUrl + `/api/course/delete/${id}`);
    }

    enrollCourse(id?: number): Observable<any> {
        return this.http.post<any>(env.backendBaseUrl + '/api/course/enroll', { courseId: id });
    }

    uploadFile(file: FormData) {

        console.log(file);

        console.log(typeof file);
        
        let headers = new HttpHeaders();
        const token = localStorage.getItem('codex-token')?.toString();
        // headers = headers.set('Content-Type', 'multipart/form-data');
        headers = headers.set('Authorization', 'Bearer ' + token)
        headers = headers.set('Access-Control-Allow-Origin', '*');
        headers = headers.set('Access-Control-Allow-Methods', 'DELETE, POST, GET, OPTIONS');
        headers = headers.set('Access-Control-Allow-Headers', 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With');
        return this.http.post<any>(env.backendBaseUrl + `/file`, file,{headers:headers});
    }

    /**
     * 
      request = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });

      request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });

      request = request.clone({ headers: request.headers.set('Accept', 'application/json') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Origin', '*') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Methods', 'DELETE, POST, GET, OPTIONS') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Headers', 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With') });
     */

}