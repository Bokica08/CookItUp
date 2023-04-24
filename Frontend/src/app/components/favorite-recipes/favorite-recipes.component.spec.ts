import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteRecipesComponent } from './favorite-recipes.component';

describe('FavoriteRecipesComponent', () => {
  let component: FavoriteRecipesComponent;
  let fixture: ComponentFixture<FavoriteRecipesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavoriteRecipesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavoriteRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
