import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Recipe } from "../models/recipe";

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
}