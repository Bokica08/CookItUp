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
    { src: 'https://scontent.fskp4-1.fna.fbcdn.net/v/t1.15752-9/345077306_1282397092405509_3226986097444120482_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=ae9488&_nc_ohc=yyP0t6QKc90AX8BOpcH&_nc_ht=scontent.fskp4-1.fna&oh=03_AdS4CS3Gh73MW8Iuq6iZ8NF5kvxUK8eFYMFIArwxIWFP2A&oe=647D6ADE', alt: 'Recipes', text: 'Diverse recipes' },
    { src: 'https://scontent.fskp4-2.fna.fbcdn.net/v/t1.15752-9/344289060_562142506060476_1627302007777585682_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=ae9488&_nc_ohc=VQtGkxXn_xAAX-1Zmqs&_nc_ht=scontent.fskp4-2.fna&oh=03_AdTlJFNZS7FdLcirwYu4g5qOFSeKKUAUgzTfb9fWHeJnvg&oe=647DD966', alt: 'Categories', text: 'Various categories' },
    { src: 'https://scontent.fskp4-1.fna.fbcdn.net/v/t1.15752-9/343857919_1201893140471363_5815012129178626399_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=ae9488&_nc_ohc=GYxJMULVN0sAX-mjkIe&_nc_ht=scontent.fskp4-1.fna&oh=03_AdTtUaaFAhDmOfdrm9Abrs0jLy9j4tpAndwU20z5UhVvNQ&oe=647D73C2', alt: 'Orders', text: 'Quick orders' }
  ];
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService,
    private customerService: CustomerService,
    private orderService: OrderService){}
  ngOnInit(): void {
  }
}
