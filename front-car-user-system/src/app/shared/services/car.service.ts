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
}
