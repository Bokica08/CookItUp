import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Register } from '../models/register';

const AUTH_API = 'http://localhost:8080/api/auth/';
const USER_KEY = 'auth-user';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'login',
      {
        username,
        password,
      },
      httpOptions
    );
  }
  logout(): Observable<any> {
    window.sessionStorage.removeItem(USER_KEY);
    return this.http.post(AUTH_API + 'logout', { }, httpOptions);
  }
  register(register:Register):Observable<any>
  {
    return this.http.post(AUTH_API+'register',register)
  }
}