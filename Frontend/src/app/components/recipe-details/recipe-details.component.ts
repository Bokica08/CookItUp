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
  img = "../../../assets/emptyheart.png"
  buttonText = "Add to favorites"
  isInUsersFavorite = false
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService){}
  ngOnInit(): void {
    // ova treba da e api povik dali e u favorite na userot
    this.isInUsersFavorite = true
    if(this.isInUsersFavorite){
        this.img = "../../../assets/blackheart.png"
        this.buttonText = "Already added to favorites"
    }
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
  addToFavorites(){
    // falat api povici
    this.isInUsersFavorite = !this.isInUsersFavorite
    if(!this.isInUsersFavorite){
      this.img = "../../../assets/emptyheart.png"
      this.buttonText = "Add to favorites"
    }
    else{
      this.img = "../../../assets/blackheart.png"
      this.buttonText = "Already added to favorites"
    }
  }
}
