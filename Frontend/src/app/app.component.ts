import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../app/_services/auth.service';
import { StorageService } from '../app/_services/storage.service';
import { ActivatedRoute, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  constructor(private storageService: StorageService, private authService: AuthService,private activateRoute: ActivatedRoute,) {
   }
  ngOnInit(): void {
    
  }
  title = 'Frontend';
  logout(): void {

  }
}
