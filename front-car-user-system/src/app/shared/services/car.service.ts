import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Car } from '../interfaces/car';

@Injectable({
  providedIn: 'root',
})
export class CarService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `${environment.apiCarsUrl}`;
  }

  findAllByUserLogged(): Observable<Car[]> {
    return this.http.get<Car[]>(this.url);
  }

  createByUserLogged(car: Car): Observable<Car> {
    return this.http.post<Car>(this.url, car);
  }

  updateByUserLogged(id: number, car: Car): Observable<Car> {
    return this.http.put<Car>(`${this.url}/${id}`, car);
  }

  deleteByUserLogged(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
