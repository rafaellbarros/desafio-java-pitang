import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

import { Observable } from 'rxjs';

import { Signin } from '../../shared/interfaces/signin';
import { TokenResponse } from '../../shared/interfaces/token.response';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `${environment.apiUrlSignin}`;
  }

  authentication(signin: Signin): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.url}`, signin);
  }
}
