import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../../../../shared/services/user.service';

import { SnackBarService } from '../../../../shared/services/snack-bar.service';
import { ConfirmationDialog } from '../../dialog/confirmation-dialog/confrimartion-dialog.component';
import { UserAddEditFormComponent } from '../user-add-edit-form/user-add-edit-form.component';
import { User } from './../../../../shared/interfaces/user';

@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'email',
    'birthday',
    'login',
    'phone',
    'action',
  ];

  dataSource!: MatTableDataSource<User>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private userService: UserService,
    private dialog: MatDialog,
    private snackBarService: SnackBarService
  ) {}

  ngOnInit(): void {
    this.getUsersList();
  }

  openAddEditUserDialog() {
    const dialogRef = this.dialog.open(UserAddEditFormComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getUsersList();
        }
      },
    });
  }

  openEditForm(data: User) {
    const dialogRef = this.dialog.open(UserAddEditFormComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getUsersList();
        }
      },
    });
  }

  deleteUser(id: number): void {
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
    this.userService.delete(id).subscribe({
      next: (res) => {
        this.snackBarService.openSnackBar('User deleted!', 'Success');
        this.getUsersList();
      },
      error: (err) => {
        console.error(err);
        this.snackBarService.openSnackBar(
          'Error while deleted the user!',
          'Error'
        );
      },
    });
  }

  private getUsersList(): void {
    this.userService.findAll().subscribe({
      next: (res) => {
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
