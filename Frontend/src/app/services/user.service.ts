import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  userUrl = 'http://localhost:8080/api/customer/';
  constructor(private http: HttpClient) {}
  getUserInfo(): Observable<Customer> {
    return this.http.get<Customer>(this.userUrl);
  }
  addToFavorite(id:number):Observable<number>
  {
    return this.http.get<number>(this.userUrl+"/addFavorite/"+id)
  }

}
