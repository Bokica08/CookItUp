import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { Category } from 'src/app/models/category';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Ingredient } from 'src/app/models/ingredient';
import { ingredientService } from 'src/app/services/ingredient.service';
import { Measure } from 'src/app/models/measure';
import { RecipeService } from 'src/app/services/recipe.service';
import { RecipeDTO } from 'src/app/models/recipedto';
import { DifficultyLevel } from 'src/app/models/difficultyLevel';
import { IngInRecipe } from 'src/app/models/ingInRecipe';
import { flatMap } from 'rxjs';
@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css'],
})
export class AddRecipeComponent implements OnInit {
  constructor(
    private activateRoute: ActivatedRoute,
    private ingredientService: ingredientService,
    private recipeService: RecipeService
  ) {}
  recipe: RecipeDTO = new RecipeDTO();
  categories: Category[] = this.activateRoute.snapshot.data['data'];
  ingredients: Ingredient[] = [];
  boxes: number[] = [1];
  images: any[] = [];
  requiredImage: File | undefined;
  optionalImage1: File | undefined;
  optionalImage2: File | undefined;
  quantities: number | undefined;
  measure: string | undefined;
  formData: FormData = new FormData();
  newIngredients: IngInRecipe[] = [];
  newIngredient: IngInRecipe = {
    name: '',
    measure: '',
    quantity: 0,
  };
  measures = Object.keys(Measure);

  ngOnInit() {
    this.getAllIngredients();
  }

  public getAllIngredients() {
    return this.ingredientService.getAllIngredients().subscribe((res) => {
      this.ingredients = res;
    });
  }
  submit(f: NgForm): void {
    console.log(this.recipe.ingredientList);

    this.recipe = {
      name: f.form.value.name,
      description: f.form.value.description,
      numPersons: f.form.value.numPersons,
      difficultyLevel: f.form.value.difficultyLevel,
      prepTime: f.form.value.prepTime,
      categoryList: f.form.value.categoryList,
      ingredientList: this.newIngredients,
    };
    console.log(this.recipe);
    let idRecipe = 0;
    this.formData.append(
      'requiredFile',
      this.requiredImage!!,
      this.requiredImage?.name
    );
    this.formData.append(
      'optionalFile1',
      this.optionalImage1!!,
      this.optionalImage1?.name
    );
    this.formData.append(
      'optionalFile2',
      this.optionalImage2!!,
      this.optionalImage2?.name
    );
    this.recipeService
      .addRecipe(this.recipe)
      .pipe(
        flatMap((res) => {
          idRecipe = res.recipeId;
          console.log('idRecipe:', idRecipe);
          return this.recipeService.addImgRecipe(this.formData, idRecipe);
        })
      )
      .subscribe(
        (res) => {
          console.log(res);
          // Handle the response of the addImgRecipe operation
        },
        (error) => {
          // Handle any errors
        }
      );
      window.location.href="/"
  }
  addBox(): void {
    const ingredient: IngInRecipe = { ...this.newIngredient };
    this.newIngredients.push(ingredient);
    this.newIngredient = {
      name: '',
      measure: Measure.grams,
      quantity: 0,
    };
  }
  onFileAdded(event: any) {
    this.requiredImage = event.target.files[0];
    console.log(this.requiredImage);
    
  }
  onOptionalFileAdded(event:any, n:number){
    if(n==1){
      this.optionalImage1 = event.target.files[0];
      console.log(this.optionalImage1);
      
    }
    else{
      this.optionalImage2 = event.target.files[0];
      console.log(this.optionalImage2);
      
    }
  }
}