import { Injectable } from '@angular/core';
import { User } from '../models/user';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {}

  clean(): void {
    window.localStorage.clear();
  }

  public saveUser(user: any): void {
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
    
  }

  public getUser(): User{
    const user = window.localStorage.getItem(USER_KEY);
      return JSON.parse(user!!);
  }

  public isLoggedIn(): boolean {
    const user = window.localStorage.getItem(USER_KEY);    
    if (user) {
      return true;
    }

    return false;
  }
}