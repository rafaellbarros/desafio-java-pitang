import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthInterceptorModule } from '../../../core/interceptors/auth.interceptor.module';
import { MaterialModule } from '../../../material.module';
import { CarAddEditFormComponent } from './car-add-edit-form/car-add-edit-form.component';
import { CarListComponent } from './car-list/car-list.component';

@NgModule({
  declarations: [CarListComponent, CarAddEditFormComponent],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild([
      {
        path: 'car',
        component: CarListComponent,
      },
    ]),
    AuthInterceptorModule,
  ],
  providers: [],
})
export class CarModule {}
