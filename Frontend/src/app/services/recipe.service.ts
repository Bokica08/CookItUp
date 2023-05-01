import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';
import { Category } from '../models/category';
import { Image } from '../models/image';
import { RecipeDTO } from '../models/recipedto';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  recipeUrl = 'http://localhost:8080/api/recipe';
  constructor(private http: HttpClient) {}
  getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipeUrl);
  }
  getNewestRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/newest`);
  }
  getMostViewedRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/mostViewed`);
  }
  getTopRatedRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/topRated`);
  }
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.recipeUrl}/getCategories`);
  }
  getAllRecipesByCategory(category: string): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/category/${category}`);
  }
  addImgRecipe(images: FormData, id: number): Observable<any> {
    return this.http.post<FormData>(this.recipeUrl + `/${id}/img`, images);
  }
  addRecipe(recipe: RecipeDTO): Observable<any> {
    return this.http.post<Recipe>(this.recipeUrl, recipe);
  }
  getRecipeDetails(id: string): Observable<Recipe> {
    return this.http.get<Recipe>(this.recipeUrl + '/details/' + id);
  }
  getDetailsForRecipe(id: string): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.recipeUrl}/details/${id}`);
  }
  searchByName(name: string): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/search/${name}`);
  }
  getRecipesCount(): Observable<number> {
    return this.http.get<number>(`${this.recipeUrl}/recipesCount`);
  }
  getCategoriesCount(): Observable<number> {
    return this.http.get<number>(`${this.recipeUrl}/categoriesCount`);
  }
  deleteRecipe(id: string): Observable<Recipe> {
    return this.http.delete<Recipe>(this.recipeUrl + '/delete/' + id);
  }
  getFilteredRecipes(
    category: string | null,
    inputText: string | null,
    difficultyLevels: string | null,
    prepTimes: string | null
  ) {
    let params = '';
    if (category) {
      params += `category=${category}&`;
    }
    if (inputText) {
      params += `inputText=${inputText}&`;
    }
    if (difficultyLevels) {
      params += `difficultyLevels=${difficultyLevels}&`;
    }
    if (prepTimes) {
      params += `prepTimes=${prepTimes}&`;
    }
    if (params) {
      params = params.slice(0, -1);
    }
    return this.http.get<Recipe[]>(`${this.recipeUrl}/filtered/?${params}`);
  }
}
