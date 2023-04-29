import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { flatMap, mergeMap, of } from 'rxjs';
import { Customer } from 'src/app/models/customer';
import { Recipe } from 'src/app/models/recipe';
import { ReviewInfo } from 'src/app/models/reviewInfo';
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
  numPersonsForOrder:number=4
  user:Customer | undefined
  userFavorites:Recipe[] | undefined
  form:FormGroup = new FormGroup({})
  constructor(private route: ActivatedRoute,
    private recipeService: RecipeService,
    private fb:FormBuilder,
    private userService:UserService,
    private reviewService:ReviewService){
      this.isLoggedIn = this.route.snapshot.data['data5'];
      this.user=this.route.snapshot.data['data6']
    }
  ngOnInit(): void {
    // ova treba da e api povik dali e u favorite na userot
    this.form= this.fb.group({
      content:"",
      stars:""
    })

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
      if(this.id!=null){
        this.getDetailsForRecipe(this.id)
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
    // falat api povici
    this.isInUsersFavorite = !this.isInUsersFavorite
    
    if(!this.isInUsersFavorite){
      this.img = "../../../assets/emptyheart.png"
      this.buttonText = "Add to favorites"
      this.userService.deleteFromFavotires(this.id!).subscribe(res=>{
        console.log(res);
        
      })
    }
    else{
      this.img = "../../../assets/blackheart.png"
      this.buttonText = "Already added to favorites"
      this.userService.addToFavorite(this.id!).subscribe(res=>{
        console.log(res);
        
      })
    }
  }
  
  onSubmit(){

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
