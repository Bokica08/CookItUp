import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ingredient } from 'src/app/models/ingredient';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-ingredient',
  templateUrl: './add-ingredient.component.html',
  styleUrls: ['./add-ingredient.component.css']
})
export class AddIngredientComponent implements OnInit{
  isLoggedIn: any;
  user: any;
  ingredient:Ingredient=new Ingredient()
  constructor(private activateRoute:ActivatedRoute,private adminService:AdminService){
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
  }
  ngOnInit(): void {
    
  }
onSubmit() {
if(this.isLoggedIn && this.user.role=="ROLE_ADMIN" && this.ingredient.description!='' && this.ingredient.name!=''
&& this.ingredient.description!=null && this.ingredient.name!=null)
{  
  this.adminService.addIngredient(this.ingredient!).subscribe(res=>
    { 
      window.location.href="/"
      
    })
}
}
}
