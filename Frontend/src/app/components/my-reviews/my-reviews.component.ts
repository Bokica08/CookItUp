import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { Review } from 'src/app/models/review';
import { RecipeService } from 'src/app/services/recipe.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-reviews',
  templateUrl: './my-reviews.component.html',
  styleUrls: ['./my-reviews.component.css']
})
export class MyReviewsComponent implements OnInit{
  public customer!: Customer;
  isLoggedIn: any;
  roles: any;
  user: any;
  showAdminBoard: any;
  showModeratorBoard: any;
  username: any;
  userAccount: any;
  myReviews: Review[] | null = null;
  constructor(
    private userService: UserService,
    private activateRoute: ActivatedRoute,
    private router: Router,
  ) {
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
  }
  ngOnInit(): void {
    console.log(this.isLoggedIn);
    
    if (this.isLoggedIn) {
      this.getReviews();
    } else {
      this.router.navigate(["/login"])
    }

  }
  getReviews() {
    this.userService.getMyReveiws().subscribe(res=>{
      this.myReviews=res;
      console.log(res);
      
    })
  }

}
