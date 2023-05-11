import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { flatMap, of } from 'rxjs';
import { Customer } from 'src/app/models/customer';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-my-recipes',
  templateUrl: './my-recipes.component.html',
  styleUrls: ['./my-recipes.component.css'],
})
export class MyRecipesComponent {
  public customer!: Customer;
  isLoggedIn: any;
  roles: any;
  user: any;
  showAdminBoard: any;
  showModeratorBoard: any;
  username: any;
  userAccount: any;
  myRecipes: Recipe[] | null = null;
  constructor(
    private activateRoute: ActivatedRoute,
    private httpClient: HttpClient,
    private router: Router,
    private recipeService:RecipeService,
  ) {
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
  }

  ngOnInit(): void {
    if (this.isLoggedIn) {
      this.roles = this.user.roles;
      this.username = this.user.username;
    } else {
      this.router.navigate(["/login"])
    }
    this.getRecipe();
  }
  getRecipe() {
    this.recipeService.getAllRecipesByUser(this.username)
      .subscribe((res) => {
        this.myRecipes = res;
      });
  }
  deleteRecipe(id:string)
  {
    this.recipeService.deleteRecipe(id)
    .pipe(
      flatMap(res => {      
      this.getRecipe();
      return of(res)
    }
      ))
    .subscribe(res=>{
    })
  }
  editRecipe(id:Number)
  {
    window.location.href = '/edit/'+id;
  }
  
}
