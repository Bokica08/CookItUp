import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  newest: Recipe[] = [];
  topRated: Recipe[] = [];
  mostViewed: Recipe[] = [];
  imageUrl: string | undefined
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService,
    private _sanitizer: DomSanitizer){}
  ngOnInit(): void {
    this.getRecipes()    
  }
  getRecipes() {
    this.recipeService.getNewestRecipes().subscribe(
      r => {
        this.newest = r;
        this.imageUrl = this.newest[0].imageList[0].byteArray
      }
    );
    this.recipeService.getMostViewedRecipes().subscribe(r => this.mostViewed = r);
    this.recipeService.getTopRatedRecipes().subscribe(r => this.topRated = r);
  }
}
