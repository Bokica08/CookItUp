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
  categoryIds: number[] = [];
  ingredients: Ingredient[] = [];
  boxes: number[] = [1];
  images: File[] = [];
  id:string | null | undefined
  requiredImage: File | undefined;
  optionalImage1: File | undefined;
  optionalImage2: File | undefined;
  quantities: number | undefined;
  measure: string | undefined;
  measures = Object.keys(Measure);
  formData: FormData = new FormData();
  newIngredients: IngInRecipe[] = [];
  newIngredient: IngInRecipe = {
    name:'',
    measure:this.measures[0],
    quantity: 1,
  };
get Categories():Number[]
{
  
  return this.recipe.categoryList.map(x=>x.categoryId);
}
  ngOnInit() {
    this.id = (this.activateRoute.snapshot.paramMap.get('id'));
    this.getAllIngredients();
    this.recipeService.getDetailsForRecipe(this.id!!).subscribe(
      res=>{
        this.recipe=res        
        this.getCategoryIds()
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
  toggleCategorySelection(event:any,category:Category) {
    let checked= (<HTMLInputElement>event).checked;
    console.log(checked);
    let index=-1
    if(this.recipe.categoryList.map(ca=>ca.categoryId).includes(category.categoryId))
    {
      index=this.recipe.categoryList.map(ca=>ca.categoryId).indexOf(category.categoryId)
      
    }
    console.log(index);
    
    if (checked && index === -1) {
      this.recipe.categoryList.push(category);
  
    } else if (!checked && index !== -1) {
      this.recipe.categoryList.splice(index, 1);
    }
    console.log(this.recipe.categoryList);

  }
  public getAllIngredients() {
    return this.ingredientService.getAllIngredients().subscribe((res) => {
      this.ingredients = res;
      this.newIngredient={
        name:this.ingredients[0].name,
        measure: this.measures[0].toString(),
        quantity: 1,

      }
    });
  }
  getCategoryIds()
  {
    this.recipe.categoryList.forEach(c => {
      this.categoryIds.push(c.categoryId)      
    });
  }
  submit(f: NgForm): void {

    this.recipe = {
      name: f.form.value.name,
      description: f.form.value.description,
      numPersons: f.form.value.numPersons,
      difficultyLevel: f.form.value.difficultyLevel,
      prepTime: f.form.value.prepTime,
      categoryList:this.recipe.categoryList,
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
