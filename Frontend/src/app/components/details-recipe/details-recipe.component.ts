import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-details-recipe',
  templateUrl: './details-recipe.component.html',
  styleUrls: ['./details-recipe.component.css']
})
export class DetailsRecipeComponent implements OnInit{
  recipe:Recipe=new Recipe()
  id:string='2'
  constructor(private recipeService:RecipeService,private route:ActivatedRoute){}
  ngOnInit(): void {
    this.id!=(this.route.snapshot.paramMap.get('id'))
    console.log(this.id);
    
     this.recipeService.getRecipeDetails(this.id).subscribe(
      res=>{
        this.recipe=res
      }
     )
  
  
  }

}


