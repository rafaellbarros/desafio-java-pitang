import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class SnackBarService {
  private durationInSeconds = 3000;

  constructor(private snackBar: MatSnackBar) {}

  openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: this.durationInSeconds,
    });
  }
}
