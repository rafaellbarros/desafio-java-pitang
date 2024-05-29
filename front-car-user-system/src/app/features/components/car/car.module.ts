import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthInterceptorModule } from '../../../core/interceptors/auth.interceptor.module';
import { CarListComponent } from './car-list/car-list.component';

@NgModule({
  declarations: [CarListComponent],
  imports: [
    CommonModule,
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
