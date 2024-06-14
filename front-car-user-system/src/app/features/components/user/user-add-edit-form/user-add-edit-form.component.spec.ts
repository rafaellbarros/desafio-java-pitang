/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { UserAddEditFormComponent } from './user-add-edit-form.component';

describe('UserAddEditComponent', () => {
  let component: UserAddEditFormComponent;
  let fixture: ComponentFixture<UserAddEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserAddEditFormComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
