import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { Category } from 'src/app/models/category';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Ingredient } from 'src/app/models/ingredient';
import { ingredientService } from 'src/app/services/ingredient.service';
import { Measure } from 'src/app/models/measure';
import { RecipeService } from 'src/app/services/recipe.service';
@Component({
  selector: 'app-add-reipe',
  templateUrl: './add-reipe.component.html',
  styleUrls: ['./add-reipe.component.css']
  
})

export class AddReipeComponent implements OnInit{
  constructor( private activateRoute: ActivatedRoute,private ingredientService:ingredientService,private recipeService:RecipeService){}
  recipe:Recipe=new Recipe()
  
  categories:Category[]=this.activateRoute.snapshot.data['data']
  ingredients:Ingredient[]=[]
  boxes: number[] = [];
  quantities:number |undefined
  measure:string|undefined
  measures=Object.keys(Measure)

   ngOnInit() {


    this.getAllIngredients()
    console.log(this.measures);
    
    
  }

    public getAllIngredients()
    {
    return this.ingredientService.getAllIngredients().subscribe(
      res=>{
        this.ingredients=res
      }
    )
    }
  submit(f:NgForm): void
  {
   this.recipeService.addRecipe(this.recipe).subscribe(res=>{console.log(res);
    this.recipeService.addImgRecipe(this.recipe.imageList).subscribe(res=>{console.log(res);
    })
   });
  }
  addBox():void{
    this.boxes.push(this.boxes.length + 1);
  }
}
