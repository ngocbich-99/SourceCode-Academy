import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class GlobalHttpInterceptor implements HttpInterceptor {

  constructor(
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log('interceptor');
    if (localStorage.getItem('codex-token') != null) {
      const token = localStorage.getItem('codex-token')?.toString();

      request = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });

      request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });

      request = request.clone({ headers: request.headers.set('Accept', 'application/json') });

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Origin', '*')})

      request = request.clone({ headers: request.headers.set('Access-Control-Allow-Headers', 'Content-Type') })

      return next.handle(request);
    } else {
      return next.handle(request);
    }
  }
}
