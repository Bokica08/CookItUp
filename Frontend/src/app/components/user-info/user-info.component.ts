import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from 'src/app/_services/storage.service';
import { Customer } from 'src/app/models/customer';
import { Order } from 'src/app/models/order';
import { Recipe } from 'src/app/models/recipe';
import { Review } from 'src/app/models/review';
import { User } from 'src/app/models/user';
import { CustomerService } from 'src/app/services/customer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css'],
})
export class UserInfoComponent implements OnInit {
  public customer!: Customer;
  isLoggedIn: any;
  roles: any;
  user: User;
  showAdminBoard: any;
  showModeratorBoard: any;
  username: any;
  text = 'test';
  userAccount: any;
  userRecipes: Recipe[] = [];
  favoriteRecipes: Recipe[] = [];
  orders: Order[] = [];
  reviews: Review[] = [];
  constructor(
    private userService: UserService,
    private activateRoute: ActivatedRoute,
    private strorageService: StorageService,
    private httpClient: HttpClient,
    private router: Router,
    private customerService: CustomerService
  ) {
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
    
  }

  ngOnInit(): void {
    if (this.isLoggedIn) {
      this.roles = this.user.role;
      this.username = this.user.username;
      this.loadFavorites()
      this.loadOrders()
      this.loadRecipes()
      this.loadReviews()
      console.log(this.reviews.length==0);
      
    } else {
      this.router.navigate(['/login']);
    }
  }
  loadRecipes(){
    this.customerService.getCustomerRecipesPreview().subscribe(data=>this.userRecipes=data)
  }
  loadFavorites(){
    this.customerService.getCustomerFavoritesPreview().subscribe(data=>this.favoriteRecipes=data)
  }
  loadReviews(){
    this.customerService.getCustomerReviewsPreview().subscribe(data=>this.reviews=data)
  }
  loadOrders(){
    this.customerService.getCustomerOrdersPreview().subscribe(data=>this.orders=data)
  }
}
