import { Component, Input, OnInit } from '@angular/core';
import { StorageService } from 'src/app/_services/storage.service';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-approve-admin',
  templateUrl: './approve-admin.component.html',
  styleUrls: ['./approve-admin.component.css']
})
export class ApproveAdminComponent implements OnInit{
  constructor(private adminService:AdminService,
    private storageService:StorageService){}
  users:Customer[] | undefined
  private roles: string[] = [];
  isLoggedIn = false;
  isAdmin = false;
  ngOnInit(): void {
    this.adminService.viewPending().subscribe(res=>{
      this.users=res
      
    })
    this.isLoggedIn = this.storageService.isLoggedIn();
    const user = this.storageService.getUser();
    if(user!=null){
    this.roles = user.role;
    if (this.roles[0] == 'ROLE_ADMIN') {
      this.isAdmin = true;
    }
  }
  }
  approve(username:string)
  {
    this.adminService.approveAdmin(username).subscribe(res=>
      {
        console.log(res);
       
      })
      this.ngOnInit()
  }
}
