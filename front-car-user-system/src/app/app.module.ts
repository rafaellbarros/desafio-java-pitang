import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CarModule } from './features/components/car/car.module';
import { LoginModule } from './features/components/login/login.module';
import { UserModule } from './features/components/user/user.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LoginModule,
    CarModule,
    UserModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
