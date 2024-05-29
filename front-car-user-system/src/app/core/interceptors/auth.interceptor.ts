import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { TokenService } from '../token/token.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private tokenService: TokenService,
    // private loginService: LoginService,
    private router: Router
  ) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.tokenService.getToken();
    const isApiCarsUrl = req.url.startsWith(environment.apiCarsUrl);

    if (token && !req.headers.has('Authorization') && isApiCarsUrl) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token),
      });

      /*
      if (this.tokenService.sessionExpired()) {
        this.alertService.warning('Sessão expirada!', true)
        this.userService.logout();
      } */

      return next.handle(authReq).pipe(
        tap({
          error: (err) => {
            if (err.status === 401) {
              this.router.navigate(['/']);
            }
          },
        })
      );
    }

    return next.handle(req);
  }
}
