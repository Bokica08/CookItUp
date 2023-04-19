import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from 'src/app/_services/storage.service';
import { Customer } from 'src/app/models/customer';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit{
  public customer!:Customer
  isLoggedIn: any;
  roles: any;
  user: any;
  showAdminBoard: any;
  showModeratorBoard: any;
  username: any;
  userAccount:any;
  constructor(private userService:UserService,private activateRoute: ActivatedRoute,private strorageService:StorageService,private httpClient:HttpClient){
    this.isLoggedIn=this.activateRoute.snapshot.data['data5']
    this.user=this.activateRoute.snapshot.data['data6']
  }

  ngOnInit(): void {
    if (this.isLoggedIn) {
      this.roles = this.user.roles;
      

      this.username = this.user.username;
    }else{
      console.log(this.isLoggedIn);
    }
    console.log(this.isLoggedIn);
    console.log(this.user);
    
    
  }
  getRecipe()
  {
    this.httpClient.get<any>("http://localhost:8080/api/customer/addFavorite/2",).subscribe(res=>
    {
      console.log(res);
      debugger
    })
  }
}