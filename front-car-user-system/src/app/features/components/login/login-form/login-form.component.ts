import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../../../../core/auth/auth.service';
import { SigninModel } from '../../../../shared/models/signin.model';
import { LoginService } from '../../../../shared/services/login.service';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  durationInSeconds = 3000;

  formLogin!: FormGroup;

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor(
    private authService: AuthService,
    private loginService: LoginService,
    private snackBar: MatSnackBar,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<LoginFormComponent>
  ) {}

  ngOnInit(): void {
    this.formInit();
  }

  private formInit(): void {
    this.formLogin = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.formLogin.valid) {
      const signin = this.formLogin.value as SigninModel;
      this.authService.authentication(signin).subscribe(
        (resp) => {
          const { token } = resp;
          const authToken = token.replace('Bearer ', '');
          if (authToken) {
            this.loginService.setToken(authToken);
            this.isAuthenticatedSubject.next(true);
            this.openSnackBar('Login successfully!', 'Success');
            this.dialogRef.close(true);
          }
        },
        (error) => {
          this.openSnackBar(`Error logging in: ${error}`, 'Error');
          console.error('Error logging in:', error);
        }
      );
    } else {
      this.openSnackBar(`Required fields!!!`, 'Warnning');
    }
  }

  openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action, {
      duration: this.durationInSeconds,
    });
  }
}
