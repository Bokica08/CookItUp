import { HttpClient } from '@angular/common/http';
import { Category } from '../models/category';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ContactForm } from '../models/contactForm';
@Injectable({
  providedIn: 'root',
})
export class ContactService {
  contactUrl = 'http://localhost:8080/api/contact';
  constructor(private httpClient: HttpClient) {}
  sendContactForm(form: ContactForm): Observable<String> {
    return this.httpClient.post<String>(`${this.contactUrl}`, form);
  }
}
