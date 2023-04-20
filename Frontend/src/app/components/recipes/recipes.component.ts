import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent {
  allRecipes: Recipe[] = [];
  category: string | null = null;
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService){}
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.category = params.get('category');
      if(this.category==null){
        this.getAllRecipes()
      }
      else{
        this.getAllRecipesByCategory(this.category)
      }
    });

    
  }
  getAllRecipes() {
    this.recipeService.getRecipes().subscribe(
      r => {
        this.allRecipes = r;
      }
    );
  }
  getAllRecipesByCategory(category:string){
    this.recipeService.getAllRecipesByCategory(category).subscribe(
      r => {
        this.allRecipes = r;
      }
    )
  }
}
