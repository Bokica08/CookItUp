import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  inputText: string | null = null;
  username: string | null = null;
  difficultyLevels: string[]= ["Easy","Medium","Hard"]
  prepTimes: string[] = ["Under 30 minutes","30 to 60 minutes","Over 60 minutes"]
  form:FormGroup = new FormGroup({})
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService,
    private fb:FormBuilder){}
  ngOnInit(): void {
    this.form = this.fb.group({
      diffLvl: null,
      prepTime: null,
    });
    this.route.paramMap.subscribe(params => {
      this.category = params.get('category');
      this.inputText = params.get('inputText');
      this.username = params.get('username');

      if(this.category==null && this.inputText==null && this.username==null){
        this.getAllRecipes()
      }
      else if(this.category!=null && this.inputText==null && this.username==null){
        this.getAllRecipesByCategory(this.category)
      }
      else if(this.inputText!=null && this.category==null && this.username==null){
        this.getAllRecipesByContaining(this.inputText)
      }
      else if(this.username!=null && this.category==null && this.inputText==null){
        this.getAllRecipesByUser(this.username)
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
  getAllRecipesByContaining(inputText:string){
    this.recipeService.searchByName(inputText).subscribe(
      r => {
        this.allRecipes = r;
      }
    )
  }
  getAllRecipesByUser(username:string){
    this.recipeService.getAllRecipesByUser(username).subscribe(
      r => {
        this.allRecipes = r;
      }
    )
  }
  onChange(){
    const diffLvlSelected = this.form.value.diffLvl;
    const prepTimeSelected = this.form.value.prepTime;
    this.recipeService.getFilteredRecipes(this.category,this.inputText,diffLvlSelected,prepTimeSelected, this.username).subscribe(it=>this.allRecipes=it)
    
  }
  clearSelection() {
    this.form.get('diffLvl')?.reset();
    this.form.get('prepTime')?.reset();
    this.onChange()
  }
}
