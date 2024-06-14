import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../../material.module';

import { UserAddEditFormComponent } from './user-add-edit-form/user-add-edit-form.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [UserListComponent, UserAddEditFormComponent],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild([
      {
        path: 'user',
        component: UserListComponent,
      },
    ]),
  ],
})
export class UserModule {}
