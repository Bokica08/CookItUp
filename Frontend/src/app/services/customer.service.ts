import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Review } from "../models/review";
import { Order } from "../models/order";
import { Recipe } from "../models/recipe";

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
    customerUrl = "http://localhost:8080/api/customer"
    constructor(private http: HttpClient) { }
    getNumberOfCustomers(): Observable<number> {
        return this.http.get<number>(`${this.customerUrl}/customersCount`);
    }
    getCustomerReviewsPreview(): Observable<Review[]> {
        return this.http.get<Review[]>(`${this.customerUrl}/myReviewsPreview`);
    }
    getCustomerOrdersPreview(): Observable<Order[]> {
        return this.http.get<Order[]>(`${this.customerUrl}/myOrdersPreview`);
    }
    getCustomerFavoritesPreview(): Observable<Recipe[]> {
        return this.http.get<Recipe[]>(`${this.customerUrl}/myFavoritesPreview`);
    }
    getCustomerRecipesPreview(): Observable<Recipe[]> {
        return this.http.get<Recipe[]>(`${this.customerUrl}/myRecipesPreview`);
    }
}