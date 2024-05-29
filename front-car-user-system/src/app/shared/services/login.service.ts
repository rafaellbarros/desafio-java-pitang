import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { TokenService } from '../../core/token/token.service';
import { Login } from '../interfaces/login';
import { TokenDecoded } from '../interfaces/token';

@Injectable({ providedIn: 'root' })
export class LoginService {
  private loginSubject = new BehaviorSubject<Login>(null!);

  constructor(private tokenService: TokenService) {
    this.tokenService.hasToken() && this.decodeAndNotify();
  }

  setToken(token: string) {
    this.tokenService.setToken(token);
    this.decodeAndNotify();
  }

  getToken() {
    return this.tokenService.getToken();
  }

  private decodeAndNotify() {
    const token = this.tokenService.getTokenDecoded();
    const login = this.createLogin(token);
    this.loginSubject.next(login);
  }

  createLogin(token: TokenDecoded | null): Login {
    return {
      ...token,
    } as any as Login;
  }

  getLogin() {
    return this.loginSubject.asObservable();
  }

  logout() {
    this.tokenService.removeToken();
    this.loginSubject.next(null!);
    // window.location.href = environment.LOGIN_UNICO_LOGOUT;
  }

  isLogged() {
    return this.tokenService.hasToken();
  }
}
