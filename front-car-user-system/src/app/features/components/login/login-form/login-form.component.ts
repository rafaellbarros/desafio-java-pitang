import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../../../../core/auth/auth.service';

import { Signin } from '../../../../shared/interfaces/signin';
import { LoginService } from '../../../../shared/services/login.service';
import { SnackBarService } from './../../../../shared/services/snack-bar.service';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  formLogin!: FormGroup;

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor(
    private authService: AuthService,
    private loginService: LoginService,
    private snackBarService: SnackBarService,
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
      const signin = this.formLogin.value as Signin;
      this.authService.authentication(signin).subscribe({
        next: (resp: any) => {
          const { token } = resp;
          const authToken = token.replace('Bearer ', '');
          if (authToken) {
            this.loginService.setToken(authToken);
            this.isAuthenticatedSubject.next(true);
            this.snackBarService.openSnackBar('Login successfully!', 'Success');
            this.dialogRef.close(true);
            this.formLogin.reset();
          }
        },
        error: (err: any) => {
          console.error('[ERROR] : ', err);
          let erroMessage = 'Error while login user!';
          if (err.error.length > 0) {
            const error = err.error[0];
            erroMessage = error.message;
          }
          this.snackBarService.openSnackBar(erroMessage, 'Error');
        },
      });
    } else {
      this.snackBarService.openSnackBar('Required fields!!!', 'Error');
    }
  }
}
