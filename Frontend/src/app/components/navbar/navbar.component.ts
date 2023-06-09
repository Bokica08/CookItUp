import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable, Subject, of } from 'rxjs';
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
    private authService:AuthService,
    private storageService:StorageService,
    private cookieService:CookieService
    ){}
  search(name: string): void {     
    this.searchTerms.next(name)  
  }
  ngOnInit(): void { 
    this.getCategories()    
    this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => {
        if (term.length > 0) {
          return this.recipeService.searchByName(term);
        } else {
          return of([]);
        }
      })
    ).subscribe((resultRecipes: Recipe[]) => {
      this.recipes = resultRecipes;
      this.searchResultsVisible = true;
    });
  } 
  getCategories() {

    this.recipeService.getCategories().subscribe((c) => (this.categories = c));
  }
  hideSearchResults() {
    this.searchResultsVisible = false;
  }
  handleButtonClick() {
    this.hideSearchResults()
    this.router.navigate(["/search/",this.inputText])
  }
  logout(){
    this.authService.logout().subscribe()
    this.cookieService.delete('bezkoder')
    this.storageService.clean()
    window.location.href="/"
  
  }
}
