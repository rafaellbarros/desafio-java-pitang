import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Car } from '../../../../shared/interfaces/car';
import { CarService } from '../../../../shared/services/car.service';
import { SnackBarService } from '../../../../shared/services/snack-bar.service';
import { ConfirmationDialog } from '../../dialog/confirmation-dialog/confrimartion-dialog.component';
import { CarAddEditFormComponent } from '../car-add-edit-form/car-add-edit-form.component';

@Component({
  selector: 'car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css'],
})
export class CarListComponent implements OnInit {
  displayedColumns: string[] = [
    'id',
    'year',
    'licensePlate',
    'model',
    'color',
    'action',
  ];

  dataSource!: MatTableDataSource<Car>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private carService: CarService,
    private dialog: MatDialog,
    private snackBarService: SnackBarService
  ) {}

  ngOnInit(): void {
    this.getCarsList();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openAddEditCarDialog() {
    const dialogRef = this.dialog.open(CarAddEditFormComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getCarsList();
        }
      },
    });
  }

  openEditForm(data: Car) {
    const dialogRef = this.dialog.open(CarAddEditFormComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getCarsList();
        }
      },
    });
  }

  deleteCar(id: number): void {
    this.openConfirmationDialog(id);
  }

  openConfirmationDialog(id: number) {
    const dialogRef = this.dialog.open(ConfirmationDialog, {
      data: {
        message: 'Are you sure want to delete?',
        buttonText: {
          ok: 'Save',
          cancel: 'No',
        },
      },
    });

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.delete(id);
      }
    });
  }

  private delete(id: number): void {
    this.carService.deleteByUserLogged(id).subscribe({
      next: (resp) => {
        this.snackBarService.openSnackBar('Car deleted!', 'Success');
        this.getCarsList();
      },
      error: (err) => {
        console.error('[ERROR] : ', err);
        let erroMessage = 'Error while deleted the car';
        if (err.error.length > 0) {
          const error = err.error[0];
          erroMessage = error.message;
        }
        this.snackBarService.openSnackBar(erroMessage, 'Error');
      },
    });
  }

  private getCarsList(): void {
    this.carService.findAllByUserLogged().subscribe({
      next: (resp) => {
        this.dataSource = new MatTableDataSource(resp);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: (err) => {
        console.log(err);
        console.error('[ERROR] : ', err);
        let erroMessage = 'Error while get all cars';
        if (err.error.length > 0) {
          const error = err.error[0];
          erroMessage = error.message;
        }
        this.snackBarService.openSnackBar(erroMessage, 'Error');
      },
    });
  }
}
