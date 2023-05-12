import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { flatMap, of } from 'rxjs';
import { Category } from 'src/app/models/category';
import { IngInRecipe } from 'src/app/models/ingInRecipe';
import { Ingredient } from 'src/app/models/ingredient';
import { Measure } from 'src/app/models/measure';
import { RecipeDTO } from 'src/app/models/recipedto';
import { ingredientService } from 'src/app/services/ingredient.service';
import { RecipeService } from 'src/app/services/recipe.service';
import { Image } from 'src/app/models/image';
import { Recipe } from 'src/app/models/recipe';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent implements OnInit {
  constructor(
    private activateRoute: ActivatedRoute,
    private ingredientService: ingredientService,
    private recipeService: RecipeService
  ) { }
  recipe: RecipeDTO = new RecipeDTO();
  categories: Category[] = this.activateRoute.snapshot.data['data'];
  user = this.activateRoute.snapshot.data['data6'];
  myRecipesIds: Number[] | null = null;
  categoryIds: number[] = [];
  ingredients: Ingredient[] = [];
  boxes: number[] = [1];
  images: Image[] = [];
  id: string | null | undefined
  idNumber:Number | undefined
  requiredImage: File | undefined;
  optionalImage1: File | undefined;
  optionalImage2: File | undefined;
  quantities: number | undefined;
  measure: string | undefined;
  measures = Object.keys(Measure);
  formData: FormData = new FormData();
  newIngredients: IngInRecipe[] = [];
  newIngredient: IngInRecipe = {
    name: '',
    measure: this.measures[0],
    quantity: 1,
  };
  get Categories(): Number[] {
    return this.recipe.categoryList.map(x => x.categoryId);
  }
  get Images(): Image[] {
    return this.images
  }
  ngOnInit() {
    this.id = (this.activateRoute.snapshot.paramMap.get('id'));
    this.idNumber=parseInt(this.id!!)
    this.getAllIngredients();
    this.recipeService.getDetailsForRecipe(this.id!!).pipe(flatMap(res => {
        this.recipe = res
        this.newIngredients = this.recipe.ingredientList;
        this.getRecipe();

        return of(res)
      }
    )
    ).subscribe(res => {
    })
    
    this.recipeService.getImagesForRecipe(this.id!!)
      .pipe(
        flatMap(res => {
          this.images = res;
          
          return of(res)
        }
        ))
      .subscribe(res => {
      })

  }
  isImageUploaded(n: number) {  
    return this.images[n] !== undefined
  }
  toggleCategorySelection(event: any, category: Category) {
    let checked = (<HTMLInputElement>event).checked;
    let index = -1
    if (this.recipe.categoryList.map(ca => ca.categoryId).includes(category.categoryId)) {
      index = this.recipe.categoryList.map(ca => ca.categoryId).indexOf(category.categoryId)

    }

    if (checked && index === -1) {
      this.recipe.categoryList.push(category);

    } else if (!checked && index !== -1) {
      this.recipe.categoryList.splice(index, 1);
    }

  }
  getRecipe() {
    this.recipeService.getAllRecipesByUser(this.user.username)
    .subscribe((res) => {
      this.myRecipesIds = res.map((x) => x.id);
      
    });

  }
  public getAllIngredients() {
    return this.ingredientService.getAllIngredients().subscribe((res) => {
      this.ingredients = res;
      this.newIngredient = {
        name: this.ingredients[0].name,
        measure: this.measures[0].toString(),
        quantity: 1,

      }
    });
  }
  submit(f: NgForm): void {

    this.recipe = {
      name: f.form.value.name,
      description: f.form.value.description,
      numPersons: f.form.value.numPersons,
      difficultyLevel: f.form.value.difficultyLevel,
      prepTime: f.form.value.prepTime,
      categoryList: this.recipe.categoryList,
      ingredientList: this.newIngredients,
    };
    let idRecipe = 0;
    
    if(this.requiredImage!=null){
        
      this.formData.append(
        'requiredFile',
        this.requiredImage!!,
        this.requiredImage?.name
      );
    }
    if(this.optionalImage1!=null){
    this.formData.append(
      'optionalFile1',
      this.optionalImage1!!,
      this.optionalImage1?.name
    );
    }
    if(this.optionalImage2!=null){
    this.formData.append(
      'optionalFile2',
      this.optionalImage2!!,
      this.optionalImage2?.name
    );
    }

    this.recipeService
      .editRecipe(this.recipe,this.id!!.toString())
      .pipe(
        flatMap((res) => {
          
          return this.recipeService.addImgRecipe(this.formData,parseInt(this.id!!));
    
        return of(res)
        })
      )
      .subscribe(
        (res) => {
         window.location.href = "/"

          
        },
        (error) => {
        }
      );
  }
  addBox(): void {
    const ingredient: IngInRecipe = { ...this.newIngredient };
    this.newIngredients.push(ingredient);
    
    this.newIngredient = {
      name: this.ingredients[0].name,
      measure: this.measures[0].toString(),
      quantity: 1,
    };
  }
  onFileAdded(event: any) {
    this.requiredImage = event.target.files[0];

  }
  onOptionalFileAdded(event: any, n: number) {
    if (n == 1) {
      this.optionalImage1 = event.target.files[0];
    }
    else {
      this.optionalImage2 = event.target.files[0];
    }

    
    
  }
}
