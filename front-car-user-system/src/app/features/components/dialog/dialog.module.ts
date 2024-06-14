import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MaterialModule } from '../../../material.module';
import { ConfirmationDialog } from './confirmation-dialog/confrimartion-dialog.component';

@NgModule({
  imports: [CommonModule, MaterialModule],
  declarations: [ConfirmationDialog],
})
export class DialogModule {}
