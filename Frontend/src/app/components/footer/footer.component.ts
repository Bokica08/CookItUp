import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  @Input() isLoggedIn = false
  @Input() isUser = false
  @Input() isAdmin = false
  constructor(private authService:AuthService, private router: Router){}
    logout(){
      this.authService.logout()
      window.location.reload()    
    }
}
