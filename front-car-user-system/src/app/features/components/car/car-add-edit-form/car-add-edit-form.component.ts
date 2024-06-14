import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Car } from '../../../../shared/interfaces/car';
import { CarService } from '../../../../shared/services/car.service';
import { SnackBarService } from '../../../../shared/services/snack-bar.service';

@Component({
  selector: 'app-car-add-edit-form',
  templateUrl: './car-add-edit-form.component.html',
  styleUrls: ['./car-add-edit-form.component.css'],
})
export class CarAddEditFormComponent implements OnInit {
  carForm!: FormGroup;

  constructor(
    private carService: CarService,
    private dialogRef: MatDialogRef<CarAddEditFormComponent>,
    private fb: FormBuilder,
    private snackBarService: SnackBarService,
    @Inject(MAT_DIALOG_DATA) public data: Car
  ) {}

  ngOnInit() {
    this.formInit();
    this.carForm.patchValue(this.data);
  }

  onSubmit() {
    if (this.carForm.valid) {
      if (this.data) {
        this.update();
      } else {
        this.create();
      }
    } else {
      this.snackBarService.openSnackBar('Required fields!!!', 'Error');
    }
  }

  private update(): void {
    const formValue = this.carForm.value;
    this.carService.updateByUserLogged(this.data.id, formValue).subscribe({
      next: (val: any) => {
        this.snackBarService.openSnackBar('Car updated!', 'Success');
        this.dialogRef.close(true);
      },
      error: (err: any) => {
        console.error('[ERROR] : ', err);
        let erroMessage = 'Error while updating the car!';
        if (err.error.length > 0) {
          const error = err.error[0];
          erroMessage = error.message;
        }
        this.snackBarService.openSnackBar(erroMessage, 'Error');
      },
    });
  }

  private create(): void {
    const formValue = this.carForm.value;

    this.carService.createByUserLogged(formValue).subscribe({
      next: (val: any) => {
        this.snackBarService.openSnackBar('Car added successfully!', 'Success');
        this.carForm.reset();
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
    this.carForm = this.fb.group({
      year: ['', Validators.required],
      licensePlate: ['', Validators.required],
      model: ['', Validators.required],
      color: ['', Validators.required],
    });
  }
}
