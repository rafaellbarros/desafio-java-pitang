import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';
import { TokenDecoded } from '../../shared/interfaces/token';

const KEY = 'authToken';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  hasToken() {
    const hasToken = !!this.getToken();
    return hasToken;
  }

  setToken(token: string) {
    localStorage.setItem(KEY, token);
  }

  removeToken() {
    localStorage.clear();
  }

  getToken() {
    return localStorage.getItem(KEY);
  }

  getTokenDecoded(): TokenDecoded | null {
    const token = this.getToken();
    if (token) {
      const tokenDecoded = jwt_decode(token) as TokenDecoded;
      return tokenDecoded;
    }
    return null;
  }

  localData() {
    const date = new Intl.DateTimeFormat('pt-BR', {
      timeZone: 'America/Sao_Paulo',
      dateStyle: 'short',
      timeStyle: 'medium',
    }).format(new Date());
    return date;
  }
}
