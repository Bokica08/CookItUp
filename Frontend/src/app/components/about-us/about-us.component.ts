import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { OrderService } from 'src/app/services/order.service';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent {
  images = [
    { src: 'https://img.freepik.com/free-vector/male-chef-mixing-flour-eggs-dough-with-whisk-hands-happy-man-apron-preparing-homemade-sweet-dessert-table-home-kitchen-flat-vector-illustration-pastry-cooking-recipe-concept_74855-21965.jpg?w=1060&t=st=1682299686~exp=1682300286~hmac=cf7ece8ccb58e55284c985529e8fb1ab76998cc9b9bf0d20d44437925d3f7f2f', alt: 'Customers', text: 'Satisfied customers', count:0 },
    { src: 'https://img.freepik.com/free-photo/soup-veggies-arrangement-flat-lay_23-2148452857.jpg?w=996&t=st=1682299735~exp=1682300335~hmac=505cd761a84a9c1b51023e455049c254cc40947544f750c3a510b5241f8ec6f1', alt: 'Recipes', text: 'Recipes to browse', count:0 },
    { src: 'https://img.freepik.com/free-vector/hands-choosing-products-category-while-doing-online-shopping-man-using-shop-application-smartphone-flat-vector-illustration-customer-making-electronic-payment-ecommerce-technology-concept_74855-24197.jpg?w=740&t=st=1682299840~exp=1682300440~hmac=55b47535c4c26c896aff9fd92f64ec160edd1b587e7038d1f475e3a24da64e74', alt: 'Categories', text: 'Categories to choose from', count:0 },
    { src: 'https://img.freepik.com/free-vector/gradient-delivery-concept-with-phone_23-2149164058.jpg?w=740&t=st=1682299870~exp=1682300470~hmac=a63fa1a02b1ebc477b5e22e77e801b068c9196c8630509b525f88410c7c6c20a', alt: 'Orders', text: 'Processed orders', count:0 }
  ];
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService,
    private customerService: CustomerService,
    private orderService: OrderService){}
  ngOnInit(): void {
    this.getAllCounts() 
  }
  getAllCounts(){
    this.recipeService.getCategoriesCount().subscribe((n)=>this.images[2].count=n)
    this.recipeService.getRecipesCount().subscribe((n)=>this.images[1].count=n)
    this.orderService.getNumberOfOrders().subscribe((n)=>this.images[3].count=n)
    this.customerService.getNumberOfCustomers().subscribe((n)=>this.images[0].count=n)
  }
}
