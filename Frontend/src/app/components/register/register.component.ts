import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/_services/auth.service';
import { Register } from 'src/app/models/register';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{
  roles=[{id:1, value:'ROLE_USER', name:"User"},{id:2, value:'ROLE_PENDING_ADMIN', name:"Admin"}]
  register=new Register()
  
    constructor(private authService:AuthService){
      this.register.role==this.roles[0]
    }

  submit(f:NgForm)
  {    
  this.authService.register(this.register).
  subscribe(res=>{
    if(res!=null && res!=undefined){
      location.href="/login";

    } 
  })
  }
  }