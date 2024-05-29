import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardCarComponent } from './car-list.component';

describe('BoardUserComponent', () => {
  let component: BoardCarComponent;
  let fixture: ComponentFixture<BoardCarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BoardCarComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BoardCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
