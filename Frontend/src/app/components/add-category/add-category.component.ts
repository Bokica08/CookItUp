import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/models/category';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit{
  isLoggedIn: any;
  user: any;
  category:Category=new Category()
  constructor(private activateRoute:ActivatedRoute,private adminService:AdminService){
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
  }
  ngOnInit(): void {
    
  }
onSubmit() {
if(this.isLoggedIn && this.user.role=="ROLE_ADMIN")
{  
  this.adminService.addCategory(this.category!).subscribe(res=>
    { 
      window.location.href="/"
    })
}
}

}
