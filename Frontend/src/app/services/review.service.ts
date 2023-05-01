import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewInfo } from '../models/reviewInfo';
import { ReviewDTO } from '../models/reviewDTO';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  reviewUrl = 'http://localhost:8080/api/recipe/addReview';
  constructor(private http: HttpClient) {}
  addReview(review: ReviewDTO, id: string): Observable<ReviewDTO> {
    return this.http.post<ReviewDTO>(`${this.reviewUrl}/${id}`, review);
  }
}
