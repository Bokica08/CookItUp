import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from 'src/app/_services/storage.service';
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
    private strorageService: StorageService,
    private httpClient: HttpClient,
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
    this.httpClient
      .get<any>('http://localhost:8080/api/customer/myFavorites')
      .subscribe((res) => {
        this.favoriteRecipes = res;
      });
  }
}
