import { Component, OnInit } from '@angular/core';
import { Car } from '../../../../shared/interfaces/car';
import { CarService } from '../../../../shared/services/car.service';

@Component({
  selector: 'car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css'],
})
export class CarListComponent implements OnInit {
  cars?: Car[];
  errors?: string;
  constructor(private carService: CarService) {}

  ngOnInit(): void {
    this.carService.findAllByUserLogged().subscribe((resp) => {
      console.log('resp', resp);
      this.cars = resp;
    });
  }
}
