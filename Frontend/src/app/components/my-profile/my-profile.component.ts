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
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})


export class MyProfileComponent implements OnInit {
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
    { src: 'https://scontent.fskp4-1.fna.fbcdn.net/v/t1.15752-9/343898926_211360974984064_5693006648702113092_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=ae9488&_nc_ohc=l1R5HGZCsVkAX-SAf2i&_nc_ht=scontent.fskp4-1.fna&oh=03_AdQGSkd5iVDep36y8lTCWyq72GwnFXbJJOzRyGQDpmigLA&oe=647D8907', alt: 'Customers', text: 'Customers', count:0 },
    { src: 'https://scontent.fskp4-1.fna.fbcdn.net/v/t1.15752-9/345077306_1282397092405509_3226986097444120482_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=ae9488&_nc_ohc=yyP0t6QKc90AX8BOpcH&_nc_ht=scontent.fskp4-1.fna&oh=03_AdS4CS3Gh73MW8Iuq6iZ8NF5kvxUK8eFYMFIArwxIWFP2A&oe=647D6ADE', alt: 'Recipes', text: 'Recipes', count:0 },
    { src: 'https://scontent.fskp4-1.fna.fbcdn.net/v/t1.15752-9/344289340_1385626078903473_8425649328455422000_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=ae9488&_nc_ohc=KFI4AAUWM1QAX8P28Ep&_nc_ht=scontent.fskp4-1.fna&oh=03_AdRiKoz6-xr_V_orXqp8tWOSXuTFLbkv92oiFPbsJSCrwg&oe=647D68BC', alt: 'Reviews', text: 'Reviews', count:0 },
    { src: 'https://scontent.fskp4-1.fna.fbcdn.net/v/t1.15752-9/343857919_1201893140471363_5815012129178626399_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=ae9488&_nc_ohc=GYxJMULVN0sAX-mjkIe&_nc_ht=scontent.fskp4-1.fna&oh=03_AdTtUaaFAhDmOfdrm9Abrs0jLy9j4tpAndwU20z5UhVvNQ&oe=647D73C2', alt: 'Orders', text: 'Orders', count:0 }
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
