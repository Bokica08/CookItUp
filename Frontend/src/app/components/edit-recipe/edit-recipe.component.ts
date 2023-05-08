import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { flatMap } from 'rxjs';
import { Category } from 'src/app/models/category';
import { IngInRecipe } from 'src/app/models/ingInRecipe';
import { Ingredient } from 'src/app/models/ingredient';
import { Measure } from 'src/app/models/measure';
import { RecipeDTO } from 'src/app/models/recipedto';
import { ingredientService } from 'src/app/services/ingredient.service';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent implements OnInit{
  constructor(
    private activateRoute: ActivatedRoute,
    private ingredientService: ingredientService,
    private recipeService: RecipeService
  ) {}
  recipe: RecipeDTO = new RecipeDTO();
  categories: Category[] = this.activateRoute.snapshot.data['data'];
  ingredients: Ingredient[] = [];
  boxes: number[] = [1];
  images: File[] = [];
  id:string | null | undefined
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
    this.id = (this.activateRoute.snapshot.paramMap.get('id'));
    this.getAllIngredients();
    this.recipeService.getDetailsForRecipe(this.id!!).subscribe(
      res=>{this.recipe=res
      }
      
    )
    this.recipeService.getImagesForRecipe(this.id!!).subscribe(res=>
      {
        this.images=res
      })
      this.formData.append(
        'requiredFile',        
        this.images[0]!!,
        this.images[0]?.name
        
      );
      this.formData.append(
        'optionalFile1',
        this.images[1]!!,
        this.images[1]?.name
      );
      this.formData.append(
        'optionalFile2',
        this.images[2]!!,
        this.images[2]?.name
      );
  }

  public getAllIngredients() {
    return this.ingredientService.getAllIngredients().subscribe((res) => {
      this.ingredients = res;
    });
  }
  submit(f: NgForm): void {

    this.recipe = {
      name: f.form.value.name,
      description: f.form.value.description,
      numPersons: f.form.value.numPersons,
      difficultyLevel: f.form.value.difficultyLevel,
      prepTime: f.form.value.prepTime,
      categoryList: f.form.value.categoryList,
      ingredientList: this.newIngredients,
    };
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
          return this.recipeService.addImgRecipe(this.formData, idRecipe);
        })
      )
      .subscribe(
        (res) => {
        },
        (error) => {
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
    
  }
  onOptionalFileAdded(event:any, n:number){
    if(n==1){
      this.optionalImage1 = event.target.files[0];
      
    }
    else{
      this.optionalImage2 = event.target.files[0];
      
    }
  }
}
