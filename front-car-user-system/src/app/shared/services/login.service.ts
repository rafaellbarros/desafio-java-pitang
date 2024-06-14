import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { TokenService } from '../../core/token/token.service';
import { Login } from '../interfaces/login';

@Injectable({ providedIn: 'root' })
export class LoginService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private loginSubject = new BehaviorSubject<Login>(null!);

  constructor(private tokenService: TokenService) {
    this.checkAuthentication();
  }

  setToken(token: string) {
    this.tokenService.setToken(token);
    this.isAuthenticatedSubject.next(true);
  }

  getToken() {
    return this.tokenService.getToken();
  }

  getLogin() {
    return this.loginSubject.asObservable();
  }

  logout() {
    this.tokenService.removeToken();
    this.isAuthenticatedSubject.next(false);
  }

  private checkAuthentication() {
    const token = this.getToken();
    const isAuthenticated = !!token;
    this.isAuthenticatedSubject.next(isAuthenticated);
  }

  isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }
}
