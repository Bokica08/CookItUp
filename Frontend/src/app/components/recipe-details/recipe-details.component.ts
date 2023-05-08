import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { flatMap, mergeMap, of } from 'rxjs';
import { StorageService } from 'src/app/_services/storage.service';
import { Customer } from 'src/app/models/customer';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
import { ReviewService } from 'src/app/services/review.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent {
  recipe: Recipe | undefined
  id: string | null = null
  img = "../../../assets/emptyheart.png"
  buttonText = "Add to favorites"
  isInUsersFavorite = false
  isLoggedIn = false
  hasAlreadyReviewed=false
  numPersonsForOrder:number=1
  user:Customer | undefined
  userFavorites:Recipe[] | undefined
  similarRecipes:Recipe[] | undefined
  form:FormGroup = new FormGroup({})
  formOrder:FormGroup = new FormGroup({})
  private roles: string[] = [];
  isAdmin=false;
  isUser = false;
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService,
    private fb:FormBuilder,
    private userService:UserService,
    private reviewService:ReviewService,
    private router:Router,
    private storageService:StorageService) {
      this.isLoggedIn = this.route.snapshot.data['data5'];
      this.user=this.route.snapshot.data['data6']
    }
  ngOnInit(): void {
    this.form= this.fb.group({
      content:"",
      stars:"1"
    })
    this.formOrder = this.fb.group({
      numPersons:1
    })
    const user = this.storageService.getUser();
    if(user!=null){
    this.roles = user.role;
    if (this.roles[0] == 'ROLE_ADMIN') {
      this.isAdmin = true;
    }
    else if(this.roles[0] == 'ROLE_USER') {
      this.isUser = true;
    }
  }

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
      if(this.id!=null){
        this.getDetailsForRecipe(this.id)
        this.getSimilarRecipes(this.id)
      }
    });
    this.userService.getFavorites().subscribe(res=>{
      this.userFavorites=res
      this.isInUsersFavorite = this.hasFavoriteRecipe()
      
    if(this.isInUsersFavorite){
      this.img = "../../../assets/blackheart.png"
      this.buttonText = "Already added to favorites"
  }
    })

  }
  getDetailsForRecipe(id:string) {
    this.recipeService.getDetailsForRecipe(id).subscribe(
      r => {
        this.recipe = r;   
        this.hasAlreadyReviewed=this.isReviewed()   
          
      }
    );
  }
  getSimilarRecipes(id:string) {
    this.recipeService.getSimilarRecipes(id).subscribe(
      r => {
        this.similarRecipes = r;   
          
      }
    );
  }
  hasFavoriteRecipe()
  {
    let flag=false
    if(this.userFavorites==null)
    {
      return flag
    }
    this.userFavorites.forEach(element => {
      if(element.id.toString()==this.id!)
      {
      flag=true
      }
      
    });
    return flag
  }
  isReviewed():boolean
  {
    let flag=false
    this.recipe?.reviews.forEach(element => {
    
      if(element.username==this.user?.username)
      {
        flag=true
      }

    });
    return flag
  }
  addToFavorites(){
    this.isInUsersFavorite = !this.isInUsersFavorite
    
    if(!this.isInUsersFavorite){
      this.img = "../../../assets/emptyheart.png"
      this.buttonText = "Add to favorites"
      this.userService.deleteFromFavotires(this.id!).subscribe(res=>{
        
      })
    }
    else{
      this.img = "../../../assets/blackheart.png"
      this.buttonText = "Already added to favorites"
      this.userService.addToFavorite(this.id!).subscribe(res=>{
      })
    }
  }
  isStarFilled(rating: number, starNumber: number): boolean {
    return starNumber <= rating;
  }
  getNStarReviewPercentage(n:number): number {
    if (this.recipe?.reviews) {
      return this.recipe.reviews.filter(r => r.stars == n).length / this.recipe.reviews.length * 100;
    } else {
      return 0;
    }
  }
  
  onSubmit(){
    if(this.form.value.stars>=1 && this.form.value.stars<=5)
    {
    this.form= this.fb.group({
      content:this.form.value.content,
      stars:this.form.value.stars
    })
    this.reviewService.addReview(this.form.value,this.id!)
    .pipe(
      flatMap(res => {      
      this.getDetailsForRecipe(this.id!);
      return of(res)
    }
      ))
    .subscribe(res=>{
    })
  }
}
  submitOrder(){
    if(this.formOrder.value.numPersons>=1 && this.formOrder.value.numPersons <=10){
    this.formOrder= this.fb.group({
      numPersonsForOrder:this.formOrder.value.numPersons      
    })    
    this.router.navigate([`/addOrder/${this.recipe?.id}/${parseInt(this.formOrder.value.numPersonsForOrder)}`])
  }
}
}
