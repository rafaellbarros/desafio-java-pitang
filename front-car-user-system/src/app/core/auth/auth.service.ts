import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

import { Observable } from 'rxjs';
import { TokenResponse } from '../../shared/interfaces/token.response';
import { SigninModel } from '../../shared/models/signin.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `${environment.apiUrlSignin}`;
  }

  authentication(signin: SigninModel): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.url}`, signin);
  }
}
