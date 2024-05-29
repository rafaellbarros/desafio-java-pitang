import { Component, OnInit } from '@angular/core';
import { User } from '../../../../shared/interfaces/user';
import { UserService } from '../../../../shared/services/user.service';

@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  users?: User[];
  errors?: string;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.findAll().subscribe((resp) => {
      console.log('resp ', resp);
      this.users = resp;
    });
  }
}
