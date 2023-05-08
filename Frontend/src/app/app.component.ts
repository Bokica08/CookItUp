import { Component, OnInit } from '@angular/core';
import { StorageService } from '../app/_services/storage.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  isAdmin = false;
  isUser = false;
  constructor(
    private storageService: StorageService,
  ) {}
  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    const user = this.storageService.getUser();
    if(user!=null){
    this.roles = user.role;
    this.username = user.username;
    if (this.roles[0] == 'ROLE_ADMIN') {
      this.isAdmin = true;
    } else if (this.roles[0] == 'ROLE_USER') {
      this.isUser = true;
    }
  }
  }
  title = 'Frontend';
  logout(): void {}
}
