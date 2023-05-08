import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { flatMap, of } from 'rxjs';
import { Customer } from 'src/app/models/customer';
import { Order } from 'src/app/models/order';
import { CustomerService } from 'src/app/services/customer.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
  public customer!: Customer;
  isLoggedIn: any;
  roles: any;
  user: any;
  showAdminBoard: any;
  showModeratorBoard: any;
  username: any;
  orderStatus: string[]= ["Created","Processing","Finished", "Canceled"]
  userAccount: any;
  form:FormGroup = new FormGroup({})
  myOrders: Order[] | null = null;
  constructor(
    private userService: UserService,
    private activateRoute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private customerService: CustomerService,
    private orderService: OrderService
  ) {
    this.isLoggedIn = this.activateRoute.snapshot.data['data5'];
    this.user = this.activateRoute.snapshot.data['data6'];
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      status: null,
    });
    if (this.isLoggedIn) {
      this.getOrders();
    } else {
      this.router.navigate(["/login"])
    }

  }
  getOrders() {
    this.userService.getMyOrders().subscribe(res => {
      this.myOrders = res;
      console.log(res);

    })
  }
  cancelOrder(orderId: string) {
    this.customerService.changeStatusToCanceled(orderId)
      .pipe(
        flatMap(res => {
          this.getOrders()
          return of(res)
        }
        ))
      .subscribe(res => {
        console.log(res);

      })
  }
  finishedOrder(orderId: string) {
    this.customerService.changeStatusToFinished(orderId)
      .pipe(
        flatMap(res => {
          this.getOrders()
          return of(res)
        }
        ))
      .subscribe(res => {
        console.log(res);

      })
  }
  onChange(){
    const statusSelected = this.form.value.status;
    this.orderService.getOrdersByStatus(statusSelected).subscribe(it=>this.myOrders=it)
    
  }
  clearSelection() {
    this.form.get('status')?.reset();
    this.getOrders();
  }

}
