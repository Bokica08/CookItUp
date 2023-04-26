import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';
import { Recipe } from '../models/recipe';
import { Review } from '../models/review';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  userUrl = 'http://localhost:8080/api/customer/';
  constructor(private http: HttpClient) {}
  getUserInfo(): Observable<Customer> {
    return this.http.get<Customer>(this.userUrl);
  }
  addToFavorite(id:string):Observable<Recipe>
  {
    return this.http.get<Recipe>(this.userUrl+"addFavorite/"+id)
  }
  getFavorites():Observable<Recipe[]>
  {
    return this.http.get<Recipe[]>(this.userUrl+"myFavorites")
  }
  deleteFromFavotires(id:string):Observable<Recipe>
  {
    return this.http.delete<Recipe>(this.userUrl+"deleteFavorite/"+id)
  }
  getMyReveiws():Observable<Review[]>
  {
   return this.http.get<Review[]>(this.userUrl+"myReviews")
  }

}
