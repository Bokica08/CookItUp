import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { OrderDto } from 'src/app/models/orderDto';
import { Recipe } from 'src/app/models/recipe';
import { OrderService } from 'src/app/services/order.service';
import { RecipeService } from 'src/app/services/recipe.service';
import { Customer } from 'src/app/models/customer'
import { CustomerService } from 'src/app/services/customer.service';
import { CustomerDto } from 'src/app/models/customerDto';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
  address: string = ''
  recipe: Recipe | undefined
  recipeId: string | null = null;
  numPersons: number | null = null;
  customer: CustomerDto | undefined
  disabled:boolean = false
  order: OrderDto = {
    numPersons: 0,
    phoneNumber: '',
    address: '',
    recipeId: 0
  }
  phoneNumber: string = ''
  constructor(private route: ActivatedRoute, private recipeService: RecipeService,
    private orderService: OrderService, private customerService: CustomerService) { }
  ngOnInit() {
    this.customerService.getCustomerPhoneNumberAndAddress().subscribe((res) => { 
      this.customer = res; 
      console.log(this.customer.address);
      console.log(this.customer.phoneNumber);      
      if(this.customer.address!=null && this.customer.phoneNumber!=null){
        this.order.address = this.customer.address
        this.order.phoneNumber = this.customer.phoneNumber
        this.disabled = true
      }
    })
    // ZEMI CUSTOMER, VIDI DALI IMA PHONENUMBER I ADDRESS, AKO IMA ZNAESH SHTO DA PRAVISH
    this.route.paramMap.subscribe(params => {
      this.recipeId = params.get('id');
      this.numPersons = parseInt(params.get('numPersons')!!);
      if (this.recipeId != null) {
        this.getDetailsForRecipe(this.recipeId)
      }
    });
  }
  onSubmit(f: NgForm) {
    this.order.numPersons = this.numPersons!!;
    this.order.recipeId = parseInt(this.recipeId!!);
    this.orderService.addOrder(this.order).subscribe((res) => {
      console.log(res);
    });
  }
  getDetailsForRecipe(id: string) {
    this.recipeService.getDetailsForRecipe(id)
      .subscribe(
        r => {
          this.recipe = r;
        }
      );
  }
}
