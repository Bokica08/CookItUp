import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Ingredient } from '../models/ingredient';
@Injectable({
  providedIn: 'root',
})
export class ingredientService {
  ingredientUrl = 'http://localhost:8080/api/ingredient';
  constructor(private httpClient: HttpClient) {}
  getAllIngredients(): Observable<Ingredient[]> {
    return this.httpClient.get<Ingredient[]>(`${this.ingredientUrl}/all`);
  }
}
