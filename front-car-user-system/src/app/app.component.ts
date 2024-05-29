import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from './core/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  eventBusSub?: Subscription;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  logout(): void {
    console.error('//TODO: Implementar');
    throw new Error('//TODO: Implementar');
  }
}
