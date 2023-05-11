import { Component } from '@angular/core';
import { StorageService } from 'src/app/_services/storage.service';
import { Recipe } from 'src/app/models/recipe';
import { User } from 'src/app/models/user';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  newest: Recipe[] = [];
  topRated: Recipe[] = [];
  mostViewed: Recipe[] = [];
  user:User =this.storageService.getUser()!;

  imageUrl: string | undefined
  constructor(private recipeService: RecipeService,private storageService:StorageService){}
  ngOnInit(): void {
    this.getRecipes() 
  }
  getRecipes() {
    this.recipeService.getNewestRecipes().subscribe(
      r => {
        this.newest = r;        
      }
    );
    this.recipeService.getMostViewedRecipes().subscribe(r => this.mostViewed = r);
    this.recipeService.getTopRatedRecipes().subscribe(r => this.topRated = r);
  }
  isStarFilled(averageRating: number, starNumber: number): boolean {
    return starNumber <= Math.floor(averageRating) ||
    (starNumber > Math.floor(averageRating) && averageRating > starNumber - 0.25 && averageRating <= starNumber + 0.25);
  }
  isStarHalfFilled(averageRating: number, starNumber: number): boolean {
    return starNumber > Math.floor(averageRating) && starNumber <= Math.ceil(averageRating)
    && averageRating > starNumber-0.75 && averageRating <= starNumber-0.25;
  }
}
