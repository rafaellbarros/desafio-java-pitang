import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from '../../../../shared/interfaces/user';
import { SnackBarService } from '../../../../shared/services/snack-bar.service';
import { UserService } from '../../../../shared/services/user.service';

@Component({
  selector: 'app-user-add-edit-form',
  templateUrl: './user-add-edit-form.component.html',
  styleUrls: ['./user-add-edit-form.component.css'],
})
export class UserAddEditFormComponent implements OnInit {
  userForm!: FormGroup;

  constructor(
    private userService: UserService,
    private dialogRef: MatDialogRef<UserAddEditFormComponent>,
    private fb: FormBuilder,
    private snackBarService: SnackBarService,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {}

  ngOnInit() {
    this.formInit();
    this.userForm.patchValue(this.data);
  }

  onSubmit() {
    if (this.userForm.valid) {
      if (this.data) {
        this.update();
      } else {
        this.create();
      }
    }
  }

  private update(): void {
    const formValue = this.userForm.value;
    formValue.birthday = this.formatDate(formValue.birthday);
    this.userService.update(this.data.id, formValue).subscribe({
      next: (val: any) => {
        this.snackBarService.openSnackBar('User updated!', 'Success');
        this.dialogRef.close(true);
      },
      error: (err: any) => {
        console.error('[ERROR] : ', err);
        let erroMessage = 'Error while updating the user!';
        if (err.error.length > 0) {
          const error = err.error[0];
          erroMessage = error.message;
        }
        this.snackBarService.openSnackBar(erroMessage, 'Error');
      },
    });
  }

  private create(): void {
    const formValue = this.userForm.value;
    formValue.birthday = this.formatDate(formValue.birthday);
    this.userService.create(formValue).subscribe({
      next: (val: any) => {
        this.snackBarService.openSnackBar(
          'User added successfully!',
          'Success'
        );
        this.userForm.reset();
        this.dialogRef.close(true);
      },
      error: (err: any) => {
        console.error('[ERROR] : ', err);
        let erroMessage = 'Error while add the user!';
        if (err.error.length > 0) {
          const error = err.error[0];
          erroMessage = error.message;
        }
        this.snackBarService.openSnackBar(erroMessage, 'Error');
      },
    });
  }

  private formInit(): void {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      birthday: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      phone: ['', Validators.required],
    });
  }

  private formatDate(date: Date): string {
    const d = new Date(date);
    const month = '' + (d.getMonth() + 1);
    const day = '' + d.getDate();
    const year = d.getFullYear();

    return [year, month.padStart(2, '0'), day.padStart(2, '0')].join('-');
  }
}
