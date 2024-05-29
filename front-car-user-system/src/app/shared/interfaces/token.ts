import { HttpHeaders } from '@angular/common/http';

export interface AccessTokenResponse {
  headers: HttpHeaders;
}

export interface TokenDecoded {
  userId: number;
  iss: string;
  login: string;
}
