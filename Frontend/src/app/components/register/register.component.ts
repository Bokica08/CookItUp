import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable, flatMap, mergeMap, mergeMapTo, of } from 'rxjs';
import { AuthService } from 'src/app/_services/auth.service';
import { Register } from 'src/app/models/register';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  roles = [{ id: 1, value: 'ROLE_USER', name: "User" }, { id: 2, value: 'ROLE_PENDING_ADMIN', name: "Admin" }]
  register = new Register()
  usernameorEmailExists: boolean = false;

  constructor(private authService: AuthService, private userService: UserService) {
    this.register.role == this.roles[0]
  }

  submit(f: NgForm) {
    if(!f.form.get('email')?.invalid){
    this.authService
      .existsByUsernameOrEmail(this.register.username!, this.register.email!)
      .pipe(
        mergeMap((res) => {
          console.log(res);
          if (res) {
            this.usernameorEmailExists = true;
            return of(res);
          }
          else {
            this.usernameorEmailExists = false;
            this.authService.register(this.register).subscribe(ress => {
              console.log(ress);
            window.location.href = "/login"
          })
            return of(res);
        }
        }))
      .subscribe(
        (res) => {
        },
        (error) => {
        }
      );  
    }
  }
}
