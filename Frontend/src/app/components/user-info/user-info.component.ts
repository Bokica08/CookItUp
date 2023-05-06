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
  username: string | null = null;
  userRecipes: Recipe[] = [];
  user: User | undefined;
  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private strorageService: StorageService,
    private httpClient: HttpClient,
    private router: Router,
    private customerService: CustomerService,
    private adminService: AdminService
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.username = params.get('username');
      if(this.username!=null){
        this.getUserDetails(this.username);
      }
    });
  }
  getUserDetails(username: string) {
    this.userService.getUserByUsername(username).subscribe(data => {
      this.user = data;
      this.loadRecipes(username);
    })
  }
  loadRecipes(username:string){
    this.customerService.userRecipesPreview(username).subscribe(data=>this.userRecipes=data)
  }

}
