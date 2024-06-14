import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `${environment.apiUsersUrl}`;
  }

  findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.url, user);
  }

  update(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.url}/${id}`, user);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
