import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment as env } from 'src/environments/environment';

@Injectable()
export class GlobalHttpInterceptor implements HttpInterceptor {

  constructor(
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (localStorage.getItem('codex-token') != null) {
      const token = localStorage.getItem('codex-token')?.toString();
    
      // console.log("url:"+request.url);
      env.backendBaseUrl+"/file"
      // console.log("url:"+ env.backendBaseUrl+"/file");
      // console.log((request.url == (env.backendBaseUrl+"/file")));
      if(request.url == (env.backendBaseUrl+"/file")){
        // console.log("url no handle"+request.url);
        
        return next.handle(request);
      }

      request = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });

      request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });

      request = request.clone({ headers: request.headers.set('Accept', 'application/json') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Origin', '*') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Methods', 'DELETE, POST, GET, OPTIONS') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Headers', 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With') });

      return next.handle(request);
    } else {
      return next.handle(request);
    }
  }
}
