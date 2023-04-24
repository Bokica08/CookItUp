import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReipeComponent } from './add-reipe.component';



describe('AddReipeComponent', () => {
  let component: AddReipeComponent;
  let fixture: ComponentFixture<AddReipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddReipeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddReipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
