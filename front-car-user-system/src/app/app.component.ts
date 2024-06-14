import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { version } from '../../package.json';
import { LoginFormComponent } from './features/components/login/login-form/login-form.component';
import { LoginService } from './shared/services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  public appVersion;
  public title = 'Car User System';
  public isAuthenticated: boolean = false;

  private durationInSeconds = 3000;

  constructor(
    private loginService: LoginService,
    private dialog: MatDialog,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.appVersion = version;
  }

  ngOnInit(): void {
    this.loginService.isAuthenticated().subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });
  }

  openLoginDialog() {
    const dialogRef = this.dialog.open(LoginFormComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.router.navigate(['/car']);
        }
      },
    });
  }

  logout(): void {
    this.loginService.logout();
    this.openSnackBar('Logout successfully!', 'Success');
    this.router.navigate(['/']);
  }

  openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: this.durationInSeconds,
    });
  }
}
