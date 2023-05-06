import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from 'src/app/_services/storage.service';
import { Customer } from 'src/app/models/customer';
import { Order } from 'src/app/models/order';
import { Recipe } from 'src/app/models/recipe';
import { Review } from 'src/app/models/review';
import { User } from 'src/app/models/user';
import { UserStatistic } from 'src/app/models/userStatistic';
import { AdminService } from 'src/app/services/admin.service';
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
  isUser: boolean = false;
  isAdmin: boolean = false;
  isPendingAdmin: boolean = false;
  recipesCreated:number=0;
  reviewsCreated:number=0;
  ordersCreated:number=0;
  customersCreated:number=0;
  users:UserStatistic[]=[];
  images = [
    { src: 'https://img.freepik.com/free-vector/male-chef-mixing-flour-eggs-dough-with-whisk-hands-happy-man-apron-preparing-homemade-sweet-dessert-table-home-kitchen-flat-vector-illustration-pastry-cooking-recipe-concept_74855-21965.jpg?w=1060&t=st=1682299686~exp=1682300286~hmac=cf7ece8ccb58e55284c985529e8fb1ab76998cc9b9bf0d20d44437925d3f7f2f', alt: 'Customers', text: 'Customers', count:0 },
    { src: 'https://img.freepik.com/free-photo/soup-veggies-arrangement-flat-lay_23-2148452857.jpg?w=996&t=st=1682299735~exp=1682300335~hmac=505cd761a84a9c1b51023e455049c254cc40947544f750c3a510b5241f8ec6f1', alt: 'Recipes', text: 'Recipes', count:0 },
    { src: 'https://img.freepik.com/premium-photo/comments-with-two-stars-concept-evaluation-rating-3d-rendering_567294-384.jpg?w=740', alt: 'Reviews', text: 'Reviews', count:0 },
    { src: 'https://img.freepik.com/free-vector/gradient-delivery-concept-with-phone_23-2149164058.jpg?w=740&t=st=1682299870~exp=1682300470~hmac=a63fa1a02b1ebc477b5e22e77e801b068c9196c8630509b525f88410c7c6c20a', alt: 'Orders', text: 'Orders', count:0 }
  ];
  constructor(
    private userService: UserService,
    private activateRoute: ActivatedRoute,
    private strorageService: StorageService,
    private httpClient: HttpClient,
    private router: Router,
    private customerService: CustomerService,
    private adminService: AdminService
  ) {
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
  }

  ngOnInit(): void {
    if (this.isLoggedIn) {
      this.roles = this.user.role;
      if(this.user.role[0].substring(5)==='ADMIN'){
        this.isAdmin=true;
      }
      else if (this.user.role[0].substring(5)==='USER'){
        this.isUser=true;
      }
      else{
        this.isPendingAdmin=true;
      }
      
      this.username = this.user.username;
      if(this.isUser){
        this.loadReviews()
        this.loadFavorites()
        this.loadOrders()
        this.loadRecipes()
      }
      else if(this.isAdmin){
        this.loadCustomersCreated()
        this.loadRecipesCreated()
        this.loadReviewsCreated()
        this.loadOrdersCreated()
        
        
        this.loadUsers()
      }
      
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
  loadRecipesCreated(){
    this.adminService.getRecipesCreated().subscribe(data=>this.images[1].count=data)
  }
  loadReviewsCreated(){
    this.adminService.getReviewsCreated().subscribe(data=>this.images[2].count=data)
  }
  loadOrdersCreated(){
    this.adminService.getOrdersCreated().subscribe(data=>this.images[3].count=data)
  }
  loadCustomersCreated(){
    this.adminService.getCustomersCreated().subscribe(data=>this.images[0].count=data)
  }
  loadUsers(){
    this.adminService.getUsers().subscribe(data=>this.users=data)
  }
}
