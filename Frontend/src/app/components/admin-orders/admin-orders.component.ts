import { Component, OnInit } from '@angular/core';
import { flatMap, of } from 'rxjs';
import { StorageService } from 'src/app/_services/storage.service';
import { orderAdmin } from 'src/app/models/orderAdmin';
import { OrderDto } from 'src/app/models/orderDto';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrls: ['./admin-orders.component.css']
})
export class AdminOrdersComponent implements OnInit{
  constructor(private adminService:AdminService,
    private storageService:StorageService){}
    private roles: string[] = [];
    isLoggedIn = false;
    isAdmin = false;
    orders:orderAdmin[]=[]
    ngOnInit(): void {
      this.adminService.viewAdminOrders().subscribe(res=>{
        this.orders=res
        
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
    changeStatus(order:orderAdmin)
    {
      this.adminService.changeStatus(order)
      .pipe(
        flatMap(res => {      
        this.ngOnInit()
        return of(res)
      }
        ))
      .subscribe(res=>{
      })
  
    }

}
