import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Recipe } from "../models/recipe";
import { Category } from "../models/category";
import { Image } from "../models/image";

@Injectable({
  providedIn: 'root'
})

export class RecipeService {
    recipeUrl = "http://localhost:8080/api/recipe"
    constructor(private http: HttpClient) { }
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
    getAllRecipesByCategory(category:string): Observable<Recipe[]> {
        return this.http.get<Recipe[]>(`${this.recipeUrl}/category/${category}`)
    }
    addImgRecipe(images:Image[]):Observable<any>{
  return  this.http.post<Image[]>(this.recipeUrl+'/img',{params:images})
    }
    addRecipe(recipe:Recipe):Observable<any>{
      return  this.http.post<Recipe>(this.recipeUrl,recipe)
    }
    getRecipeDetails(id:string):Observable<Recipe>{
        return this.http.get<Recipe>(this.recipeUrl+'/details/'+id)
    }
    getDetailsForRecipe(id:string): Observable<Recipe> {
        return this.http.get<Recipe>(`${this.recipeUrl}/details/${id}`)
    }
    searchByName(name:string):Observable<Recipe[]> {
        return this.http.get<Recipe[]>(`${this.recipeUrl}/search/${name}`)
    }
    getRecipesCount():Observable<number> {
        return this.http.get<number>(`${this.recipeUrl}/recipesCount`)
    }
    getCategoriesCount():Observable<number> {
        return this.http.get<number>(`${this.recipeUrl}/categoriesCount`)
    }
    
}