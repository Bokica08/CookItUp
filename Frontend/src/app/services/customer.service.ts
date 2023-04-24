import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
    customerUrl = "http://localhost:8080/api/customer"
    constructor(private http: HttpClient) { }
    getNumberOfCustomers(): Observable<number> {
        return this.http.get<number>(`${this.customerUrl}/customersCount`);
    }
}