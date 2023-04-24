import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class OrderService {
    orderUrl = "http://localhost:8080/api/order"
    constructor(private http: HttpClient) { }
    getNumberOfOrders(): Observable<number> {
        return this.http.get<number>(`${this.orderUrl}/ordersCount`);
    }
}