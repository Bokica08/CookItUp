import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { flatMap, of } from 'rxjs';
import { Customer } from 'src/app/models/customer';
import { Recipe } from 'src/app/models/recipe';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-favorite-recipes',
  templateUrl: './favorite-recipes.component.html',
  styleUrls: ['./favorite-recipes.component.css']
})
export class FavoriteRecipesComponent {
  public customer!: Customer;
  isLoggedIn: any;
  roles: any;
  user: any;
  showAdminBoard: any;
  showModeratorBoard: any;
  username: any;
  userAccount: any;
  favoriteRecipes: Recipe[] | null = null;
  constructor(
    private userService: UserService,
    private activateRoute: ActivatedRoute,
    private router: Router
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
    this.userService.getFavorites().subscribe(res=>
      {
        this.favoriteRecipes=res
      })
  }
  removeFromFavorites(id:string)
  {
    this.userService.deleteFromFavotires(id)
    .pipe(
      flatMap(res => {      
      this.getRecipe();
       return of(res)
    }
      ))
    .subscribe(res=>{
    })

  }
}
