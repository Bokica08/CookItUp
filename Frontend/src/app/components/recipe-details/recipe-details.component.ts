import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent {
  recipe: Recipe | undefined
  id: string | null = null
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService){}
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
      if(this.id!=null){
        this.getDetailsForRecipe(this.id)
      }
    });

    
  }
  getDetailsForRecipe(id:string) {
    this.recipeService.getDetailsForRecipe(id).subscribe(
      r => {
        this.recipe = r;
        console.log(r.reviews);
        
      }
    );
  }
}
