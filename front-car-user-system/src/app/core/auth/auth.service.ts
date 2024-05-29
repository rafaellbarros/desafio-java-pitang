import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

import { AccessTokenResponse } from '../../shared/interfaces/token';
import { LoginService } from '../../shared/services/login.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private url: string;

  constructor(
    private http: HttpClient,
    private loginService: LoginService,
    private router: Router
  ) {
    this.url = `${environment.apiUrl}/auth`;
  }

  authentication(login: string, password: string) {
    const route = this.router;
    const service = this.loginService;
    return this.http
      .post<AccessTokenResponse>(
        `${this.url}/login`,
        { login, password },
        { observe: 'response' as 'body' }
      )
      .pipe(
        catchError((err) => {
          route.navigate(['/']);
          throw err;
        })
      )
      .subscribe((resp) => {
        const authorization = resp.headers.get('Authorization') as string;
        const authToken = authorization.replace('Bearer ', '');
        if (authToken) {
          service.setToken(authToken);
        }
        route.navigate(['/']);
      });
  }
}
