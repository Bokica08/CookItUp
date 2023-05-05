import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderDto } from '../models/orderDto';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  orderUrl = 'http://localhost:8080/api/order';
  constructor(private http: HttpClient) {}
  getNumberOfOrders(): Observable<number> {
    return this.http.get<number>(`${this.orderUrl}/ordersCount`);
  }
  addOrder(order: OrderDto): Observable<OrderDto> {
    return this.http.post<OrderDto>(`${this.orderUrl}`, order);
  }
  getOrdersByStatus(status: string): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl + '/searchByStatus?status=' + status);

  }
}
