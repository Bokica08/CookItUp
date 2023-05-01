import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime,
  distinctUntilChanged,
  switchMap,
  tap,
} from 'rxjs/operators';
import { AuthService } from 'src/app/_services/auth.service';
import { StorageService } from 'src/app/_services/storage.service';
import { Category } from 'src/app/models/category';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  categories: Category[] = [];    
  recipes : Recipe[] | undefined
  searchTerms = new Subject<string>();
  searchResultsVisible = false;
  inputText = ''
  @Input() isLoggedIn = false
  @Input() isUser = false
  @Input() isAdmin = false
  user = null
  constructor(private recipeService: RecipeService,
    private router:Router,
    private activateRoute:ActivatedRoute,
    private authService:AuthService,
    private storageService:StorageService){}
  search(name: string): void { 
    this.searchTerms.next(name)  
  }
  ngOnInit(): void { 
    this.getCategories()    
    this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.recipeService.searchByName(term))
    ).subscribe((resultRecipes:Recipe[])=>{
      this.recipes = resultRecipes;
      this.searchResultsVisible = true;
    })    
        
  } 
  getCategories() {

    this.recipeService.getCategories().subscribe((c) => (this.categories = c));
  }
  hideSearchResults() {
    this.searchResultsVisible = false;
  }
  handleButtonClick() {
    this.hideSearchResults()
    console.log(this.inputText)
    this.router.navigate(["/search/",this.inputText])
  }
  logout(){
    console.log("hi");
    this.storageService.clean()
    this.authService.logout()
    window.location.href="/"
  
  }
}
