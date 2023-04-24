import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/models/category';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  categories: Category[] = [];
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService){}
  ngOnInit(): void {
    this.getCategories()   
    console.log(this.categories);
    
    
  }
  getCategories() {
    this.recipeService.getCategories().subscribe(c => this.categories = c)

  }
}
